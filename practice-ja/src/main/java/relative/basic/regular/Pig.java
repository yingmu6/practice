package relative.basic.regular;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/03/23 14:39
 * @Description
 */
@Setter
@Getter
public class Pig {

    @EntityDetail(name = "小猪")
    private String animalName;

    @EntityDetail(describe = "白色的小猪")
    private String animalDesc;
}
