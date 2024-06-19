package relative.basic.regular;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/03/23 14:37
 * @Description
 */
@Setter
@Getter
public class Dog {

    @EntityDetail(name = "小狗") // 使用正则表达式 @EntityDetail\(\D*\) 去查找，找到两个@EntityDetail连接在一起了
    private String animalName;

    @EntityDetail(describe = "黑色的小狗")
    private String animalDesc;
}
