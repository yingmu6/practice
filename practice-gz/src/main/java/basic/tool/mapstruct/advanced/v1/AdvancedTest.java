package basic.tool.mapstruct.advanced.v1;

import com.alibaba.fastjson.JSON;
import org.mapstruct.factory.Mappers;

/**
 * @Author chenSy
 * @Date 2023/03/27 20:41
 * @Description
 */
public class AdvancedTest {
    public static void main(String[] args) {

        Car car = new BioDieselCar();
        car.setId(1);
        car.setName("柴油车");

        // 方式一（错误使用）：该种方式使用匿名类方式，是不会进行@BeforeMapping、@AfterMapping修饰的逻辑的
        CarsMapper carsMapper = new CarsMapper() {
            @Override
            public CarDTO toCarDto(Car car) {
                CarDTO carDTO = new CarDTO();
                carDTO.setId(1000);
                carDTO.setName("haha:" + car.getName());
                return carDTO;
            }
        };

        // 方式二（不推荐使用）：@Mapper定义抽象类，然后定义before、after的方法，@Mapstruct会进行把这两个方法编织进行
        CarDTO carDTO = carsMapper.toCarDto(car);
        System.out.println("转换的结果V1：" + JSON.toJSONString(carDTO));

        CarsMapper mapper = new CarsMapperImpl(); //虽然可以直接使用Mapper生成的实现类，但是这样不太好，大部分开发人员，面对的都是源码而不是字节码文件
        CarDTO carDTO2 = mapper.toCarDto(car);
        System.out.println("转换的结果V2：" + JSON.toJSONString(carDTO2));

        // 方法三（推荐使用，通过Mappers.getMapper来获取实例）
        CarsMapper carsMapper3 = Mappers.getMapper(CarsMapper.class);
        CarDTO carDTO3 = carsMapper3.toCarDto(car);
        System.out.println("转换的结果V3：" + JSON.toJSONString(carDTO3));
    }
}
