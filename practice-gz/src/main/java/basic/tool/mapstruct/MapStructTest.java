package basic.tool.mapstruct;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chensy
 * @date 2022/5/11
 */
public class MapStructTest {
    /**
     * MapStruct 官方文档 https://mapstruct.org/
     *           github地址：https://github.com/mapstruct/mapstruct/
     *           好的介绍文档：https://www.baeldung.com/mapstruct
     *
     * 1.What is it?
     * MapStruct is a code generator that greatly simplifies the implementation of mappings between
     * Java bean types based on a convention over configuration approach.
     * （MapStruct对于Java Bean类型的转换是极其简单的）
     *
     * 2.Why？
     * Writing such mapping code is a tedious（冗长的） and error-prone（容易出错） task. MapStruct aims at simplifying this work by automating it as much as possible.
     * （手写转换代码，是冗长的且容易出错。而Mapstruct是简单的，能自动产生映射代码）
     *
     * 3.How？
     * MapStruct is an annotation processor which is plugged into the Java compiler and can be used in command-line builds (Maven, Gradle etc.)
     * as well as from within your preferred IDE.
     *
     * 使用方式
     * 1）在接口上使用@Mapper
     * 2）使用Mappers.getMapper(Class class) 创建接口实例
     * 3）不同对象之间的属性有差异时，使用@Mapping进行指定name或type
     */

    public static void main(String[] args) {
        basicUse();
        useMultiVar();
        formatDate();
    }

    /**
     * 场景1：基本转换
     */
    private static void basicUse() {
        IAnimalConverter animalConverter = IAnimalConverter.INSTANCE;

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
        System.out.println("基本使用：" + JSON.toJSONString(animalBO));
    }


    /**
     * 场景2：多个对象的属性值转换为一个对象的属性值
     */
    private static void useMultiVar() {
        DogBO dogBO = new DogBO();
        dogBO.setDogName("小狗");
        dogBO.setDogColor("黑色的");

        PigBO pigBO = new PigBO();
        pigBO.setPigName("小猪");
        pigBO.setPigColor("白色的");

        MammalBO mammalBO = IAnimalConverter.INSTANCE.toMammalBO(dogBO, pigBO);
        System.out.println("多个参数转换：" + JSON.toJSONString(mammalBO));
    }

    /**
     * 场景3：使用表达式做计算
     */

    /**
     * 场景4：使用枚举值
     */

    /**
     * 场景5：忽略字段（从场景2中看，birthday值为null，JSON对应的字符串属性没有打印出来，不知道其它序列化工具会不会打印出来）
     */


    /**
     * 场景6：使用@BeforeMapping、@AfterMapping注解
     */

    /**
     * 场景7：注入spring 主键到Mapper
     */

    /**
     * 场景8：格式化时间
     */
    private static void formatDate() {
        DogBO dogBO = new DogBO();
        dogBO.setDogName("小黑");
        dogBO.setBirthDate(new Date());

        MammalBO mammalBO = IAnimalConverter.INSTANCE.toMammalBOWithDateFormat(dogBO);
        System.out.println("时间格式化：" + JSON.toJSONString(mammalBO));
    }

}




