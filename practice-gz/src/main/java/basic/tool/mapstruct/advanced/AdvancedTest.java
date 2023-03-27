package basic.tool.mapstruct.advanced;

import com.alibaba.fastjson.JSON;

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

        // 方式二（正确使用）：@Mapper定义抽象类，然后定义before、after的方法，@Mapstruct会进行把这两个方法编织进行
        CarDTO carDTO = carsMapper.toCarDto(car);
        System.out.println("转换的结果V1：" + JSON.toJSONString(carDTO));

        CarsMapper mapper = new CarsMapperImpl();
        CarDTO carDTO2 = mapper.toCarDto(car);
        System.out.println("转换的结果V2：" + JSON.toJSONString(carDTO2));
    }
}
