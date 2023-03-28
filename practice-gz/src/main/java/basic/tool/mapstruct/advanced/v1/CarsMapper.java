package basic.tool.mapstruct.advanced.v1;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @Author chenSy
 * @Date 2023/03/27 20:38
 * @Description
 */
@Mapper
public abstract class CarsMapper { //定义抽象类，然后定义before、after的方法，@Mapstruct会进行把这两个方法编织进行
    @BeforeMapping
    protected void enrichDTOWithFuelType(Car car, @MappingTarget CarDTO carDto) {
        if (car instanceof ElectricCar) {
            carDto.setFuelType(FuelType.ELECTRIC);
        }
        if (car instanceof BioDieselCar) {
            carDto.setFuelType(FuelType.BIO_DIESEL);
        }

        System.out.println("调用mapping method前");
    }

    @AfterMapping
    protected void convertNameToUpperCase(@MappingTarget CarDTO carDto) {
        carDto.setName(carDto.getName().toUpperCase());
        System.out.println("调用mapping method后");
    }

    public abstract CarDTO toCarDto(Car car);
}