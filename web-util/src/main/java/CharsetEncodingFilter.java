import javax.servlet.*;
import java.io.IOException;

/**
 * 字符编码过滤器
 * @author : chensy
 * Date : 2020-03-09 20:20
 */
public class CharsetEncodingFilter implements Filter {
    private static String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response); // 执行过滤，包含方法的具体执行

    }

    @Override
    public void destroy() {

    }
}
