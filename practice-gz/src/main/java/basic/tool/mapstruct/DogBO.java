package basic.tool.mapstruct;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author chenSy
 * @Date 2023/03/25 22:04
 * @Description
 */
@Setter
@Getter
public class DogBO {

    private String dogName;

    private String dogColor;

    private Date birthDate;
}
