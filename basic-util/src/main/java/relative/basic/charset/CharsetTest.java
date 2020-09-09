package relative.basic.charset;

import java.nio.charset.Charset;

/**
 * @author : chensy
 * Date : 2020/9/9 下午12:41
 */
public class CharsetTest {
    public static void main(String[] args) {
        System.out.println(Charset.forName("gbk")); //输出"GBK"
        System.out.println(Charset.defaultCharset()); //输出"UTF-8"
    }
}
