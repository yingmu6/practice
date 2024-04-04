import com.orange.api.IUserInfoApi;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : chensy
 * Date : 2020-03-12 12:19
 */
public class SaasAppTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"saas-app.xml"});
        context.start();

        IUserInfoApi userInfoApi = (IUserInfoApi) context.getBean("userInfoApi");

        System.out.println("收到的SaaS信息：" + userInfoApi.getUserInfo(1L));

        System.in.read();
    }
}
