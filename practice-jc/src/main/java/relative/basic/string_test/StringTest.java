package relative.basic.string_test;


import org.apache.commons.lang3.StringUtils;

/**
 * @author chensy
 * @date 2022/5/31
 */
public class StringTest {
    public static void main(String[] args) {
        trimUse();
    }

    // 去空格
    public static void trimUse() {
        String str = " aaa bbb ";
        System.out.println(str.trim());

        String str2 = " ccc ddd ";
        System.out.println(StringUtils.deleteWhitespace(str2));

        String str3 = null;
        System.out.println(StringUtils.deleteWhitespace(str3));
    }
}
