package basic.tool.mapstruct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/03/25 10:16
 * @Description
 */
@Getter
@Setter
@ToString
class AnimalVO {

    private String name;

    private Integer price;

    private String colorName;

    private Double weight;

    private Integer animal;

    private List<String> names;
}
