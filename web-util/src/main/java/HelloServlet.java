import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet 简单实例  https://www.runoob.com/servlet/servlet-first-example.html
 * https://www.ibm.com/developerworks/cn/java/j-lo-servlet/index.html  Servlet 工作原理解析
 * 处理请求和发送响应的过程是由一种叫做Servlet的程序来完成的，并且Servlet是为了解决实现动态页面而衍生的东西
 * Servlet是一种允许响应请求的Java类。  https://www.jianshu.com/p/e0471ce1b106
 *
 * @author : chensy
 * Date : 2020-03-09 19:49
 */
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        // 执行必需的初始化
        message = "22 Hello World 你好好吗";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html");
        /**
         * 此处可以在response设置contentType类型解决,但是需要在每处响应的地方写：（重复代码，代码倾入性比较多）
         * 做好写过滤器Filter，进行拦截处理
         */
        //response.setContentType("text/html;charset=UTF-8");

        // 实际的逻辑是在这里（目前输出有中文乱码、写过滤器Filter处理）
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy() {
        // 什么也不做
    }

    /**
     * 1. java Web目录结构：https://blog.csdn.net/l00149133/article/details/78984083
     * 2. idea创建Java Web项目 https://blog.csdn.net/qq_27093465/article/details/63683873
     * 3. 更改idea中JDK版本 https://blog.csdn.net/caoxiaohong1005/article/details/78698779
     * 4. IntelliJ IDEA 编译报错：Error:java: Compilation failed: internal java compiler error
     *   https://blog.csdn.net/qq_27093465/article/details/53261939
     */
}
