package basic.tool.mapstruct.basic;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author chenSy
 * @Date 2023/03/25 10:16
 * @Description
 */
@Mapper
public interface IAnimalConverter {
    /**
     * 需要引入的包有： mapstruct、mapstruct-processor（会为接口生成实现类，相当于是@Mapper注解的解析器）
     * 声明了@Mapper注解，
     */

    IAnimalConverter INSTANCE = Mappers.getMapper(IAnimalConverter.class);

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

    @Mappings({
            @Mapping(target = "name", source = "dogBO.dogName"),
            @Mapping(target = "color", source = "pigBO.pigColor")
    })
    MammalBO toMammalBO(DogBO dogBO, PigBO pigBO);

    @Mappings({
            @Mapping(target = "name", source = "dogBO.dogName"),
            @Mapping(target = "color", source = "pigBO.pigColor"),
    })
    MammalBO toMammalBO(DogBO dogBO, PigBO pigBO, MammalBO2 mammalBO2); //属性，若有指定Mapping，就按指定的来，未指定则会找参数对象中相同的属性进行映射

    @Mapping(source = "dogBO.dogName", target = "name")
    @Mapping(source = "dogBO.birthDate", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    MammalBO toMammalBOWithDateFormat(DogBO dogBO);

    @Mapping(source = "pigName", target = "name")
    @Mapping(target = "mammalColor", expression = "java(ColorEnum.getByCode(pigBO.getPigColor()))")
    MammalBO2 toMammalBO2ByExpress(PigBO pigBO);

    @Mapping(target = "pigColor", expression = "java(mammalBO2.getMammalColor().getCode())")
    PigBO toPigBOUseEnum(MammalBO2 mammalBO2);

    @Mapping(target = "color", ignore = true)
    @Mapping(target = "name", ignore = true)
    MammalBO toMammalBOUseIgnore(PigBO pigBO);

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
}
