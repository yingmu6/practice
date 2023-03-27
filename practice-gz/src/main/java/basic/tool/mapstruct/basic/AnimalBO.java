package basic.tool.mapstruct.basic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/03/25 10:15
 * @Description
 */
@Setter
@Getter
public class AnimalBO {
    private String name;

    private Integer price;

    private String color;

    private Double weight;

    private Boolean isAnimal;

    private List<String> names;
}
