package basic.tool.mapstruct;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2022/5/11
 */
public class MapStructTest {
    public static void main(String[] args) {

        AnimalConverter animalConverter = AnimalConverter.INSTANCE;

        AnimalVO animalVO = new AnimalVO();
        animalVO.setName(null);
        animalVO.setColorName(" 红  色  ");
        animalVO.setPrice(11);
        animalVO.setAnimal(1);
        animalVO.setWeight(11.288); //duble类型也会映射，只是若后位是0，会去掉，如11.20，转换后位11.2

        List<String> names = new ArrayList<>();
        names.add("张三");
        animalVO.setNames(names);

        AnimalBO animalBO = animalConverter.toAnimalBO(animalVO);
        System.out.println(JSON.toJSONString(animalBO));


//        AnimalBO animalBO = new AnimalBO();
//        animalBO.setName("jaja");
//
//        String str = JSON.toJSONString(animalBO);
//        AnimalBO animalBO1 = JSON.parseObject(str, AnimalBO.class);
//        System.out.println(animalBO1);
    }
}

@Mapper
interface AnimalConverter {

    /**
     * 需要引入的包有： mapstruct、mapstruct-processor（会为接口生成实现类，相当于是@Mapper注解的解析器）
     * 声明了@Mapper注解，
     */

    AnimalConverter INSTANCE = Mappers.getMapper(AnimalConverter.class);

    @Mappings({
//            @Mapping(target = "name", ignore = true),
//            @Mapping(target = "weight", ignore = true),
            //ignore：忽略是指将值忽略，不是将字段忽略，字段依然存在，
            // 只是为null（有些业务场景null的字段可能不返回，达到去掉字段的效果）
            // @Mapping(target = "weight2", ignore = true)  会检查字段是否存在，字段名不对会报错误，

            //两个转换的对象，若有差异字段，若不指示清楚，值就会丢失，但不会报错
//            @Mapping(source = "colorName", target = "color"),

            // 使用java 表达式 source与expression只能任意定义一个（也就是可以直接将source直接转target，也可以将source经过expression表达式处理后再转）
            // @Mapping(source = "color", target = "colorName", expression = "java(animalVO.getColor().length())")

            @Mapping(target = "price", expression = "java(animalVO.getPrice() + 1)"),

//            @Mapping(target = "color", expression = "java(org.apache.commons.StringUtils.deleteWhitespace(animalVO.getColorName()) + \"haha\")"),
            @Mapping(target = "isAnimal", expression = "java(1==animalVO.getAnimal()?false:true)")
    })
    AnimalBO toAnimalBO(AnimalVO animalVO);

    //todo @csy-08-10 怎么进行循环变量（将对象列表进行转换）
    // /**
    //     * 钥匙类型列表
    //     */
    //    private List<AccessKeyTypeEnum> accessKeyTypes;
}

/**
 * 表达式中可以调用：方法，列如：
 *
 * @Mappings({
 * @Mapping(target = "data", expression = "java(toDeserializeData(request.getData()))") //可以定义一个default方法，进行调用
 * })
 * DxxRequestBO toDxxRequestBO(DxxRequest request);
 * <p>
 * default DxxRequestBO toDeserializeData(String data) {
 * try {
 * return JSON.parseObject(data, DxxRequestBO.class);
 * } catch (Exception e) {
 * return null;
 * }
 * }
 */

@Getter
@Setter
class AnimalBO {

    private String name;

    private Integer price;

    private String color;

    private Double weight;

    private Boolean isAnimal;

    private List<String> names;
}

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