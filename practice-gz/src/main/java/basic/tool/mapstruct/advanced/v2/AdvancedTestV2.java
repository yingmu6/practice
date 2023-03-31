package basic.tool.mapstruct.advanced.v2;

import com.alibaba.fastjson.JSON;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author chenSy
 * @Date 2023/03/28 10:56
 * @Description
 */
public class AdvancedTestV2 {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/spring-bean-test.xml"});
        context.start();

        //组件模型为spring时，会以Spring组件形式提供，可通过spring获取到bean（这个特性太牛了）
        UsingInjectService usingInjectService = context.getBean(UsingInjectService.class);

        //声明@Mapper(componentModel = "spring") 后，Mapper的实现类就会带上@Component，就可以按依赖注入做很多事了
        PigBOV2 pigBOV2 = new PigBOV2();
        pigBOV2.setPigName("小黑猪");
        AnimalBOV2 animalBOV2 = usingInjectService.toAnimalBOV2(pigBOV2);

        System.out.println("结果值：" + JSON.toJSONString(animalBOV2));
    }
}
