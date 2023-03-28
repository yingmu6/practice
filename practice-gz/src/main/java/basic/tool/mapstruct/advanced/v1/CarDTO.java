package basic.tool.mapstruct.advanced.v1;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/03/27 20:37
 * @Description
 */
@Setter
@Getter
public class CarDTO {
    private int id;
    private String name;
    private FuelType fuelType;
}
