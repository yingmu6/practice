package relative.basic.regular;

import com.google.common.collect.Lists;
import lombok.val;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 正则表达式定义了字符串的模式。
 * 正则表达式可以用来搜索、编辑或处理文本。
 * 正则表达式并不仅限于某一种语言，但是在每种语言中有细微的差别。
 *
 * https://www.javatpoint.com/java-regex  正则表达式英文使用文档
 *
 * @author chensy
 * @date 2019-05-30 23:26
 */
public class RegularTest {
    /**
     * 正则表达式概述：
     * 1）A Regular Expression – or regex for short– is a syntax that allows you to match strings with specific patterns.
     * （正则表达式(Regular Expression，简称regex)是一种允许你将字符串与特定模式匹配的语法）
     *
     * 2）The Java Regex or Regular Expression is an API to define a pattern for searching or manipulating strings.
     * （正则表达式用来搜索和操作字符串）
     *
     * 3）Java Regex API provides 1 interface and 3 classes in java.util.regex package.
     * （java.util.regex包下主要提供了一个接口、三个类），具体如下：
     * a）MatchResult interface
     * b）Matcher class:（It implements the MatchResult interface. It is a regex engine which is used to perform match operations on a character sequence.
     *                    实现了MatchResult接口，它是一个正则表达式引擎，用于对字符序列执行匹配操作）
     * c）Pattern class：（It is the compiled version of a regular expression. It is used to define a pattern for the regex engine.
     *                    它是正则表达式的编译版本。它用于定义正则表达式引擎的模式）
     * d）PatternSyntaxException class
     *
     * 4）预定义字符类 （元字符，可以直接使用）
     *    \D  非数字字符：[^0-9]
     *    \d  数字字符：[0-9]
     *    \w  单词字符：[a-zA-Z_0-9]
     *    \W  非单词字符：[^\w]
     *
     * 参考链接：
     * a）正则表达式详解：https://www.javatpoint.com/java-regex (测试用例参考的网址)
     * b）正则表达式规则：https://coderpad.io/regular-expression-cheat-sheet （定义了正则表达式中符号的含义，可对照着解释正则表达式）
     * c）正则表达式在线测试：https://c.runoob.com/front-end/854 （可多用在线测试工具，来实践验证[里面还包含语法说明，比较详细]）
     */

    /**
     * 场景1：多种使用方式
     */
    @Test
    public void test_multi_way() { //进行匹配的方式

        //第一种方式：（分开定义Match、Pattern）
        Pattern p = Pattern.compile(".s"); //编译指定的表达式，生成Pattern实例
        Matcher m = p.matcher("as"); //创建匹配器
        boolean b = m.matches(); //判断字符串是否匹配成功

        //第二种方式：不需要产生中间变量，使用级联操作
        boolean b2 = Pattern.compile(".s").matcher("as").matches();

        //第三种方式：在创建Pattern时，指定正则表达式和匹配的字符串
        boolean b3 = Pattern.matches(".s", "as");

        System.out.println(b+" "+b2+" "+b3);
    }

    /**
     * 场景2：比较指定的字符
     */
    @Test
    public void test_special_char() {
        /**
         * 规则说明：
         * ".s" 匹配两个字符，并且最后一个字符需要为's'
         * "..s" 匹配三个字符，并且最后一个字符需要为's'
         * "..s.s" 匹配五个字符，并且第三个和最后一个字符都需要为's'
         */
        System.out.println(Pattern.matches(".s", "as"));//true (2nd char is s)
        System.out.println(Pattern.matches(".s", "mk"));//false (2nd char is not s)
        System.out.println(Pattern.matches(".s", "mst"));//false (has more than 2 char)
        System.out.println(Pattern.matches(".s", "amms"));//false (has more than 2 char)
        System.out.println(Pattern.matches("..s", "mas"));//true (3rd char is s)
        System.out.println(Pattern.matches("..s.s", "masas"));//true
    }

    /**
     * 场景N：几个特殊正则表达式测试
     * a）"[\\-._0-9a-zA-Z]+"
     * b）"[*,\\-._0-9a-zA-Z]+
     * c）[a-zA-Z][0-9a-zA-Z]*
     */

    @Test
    public void findAndReplace() {
        String str = "@EntityDetail88(name = \"小猪\")"; //使用"@EntityDetail(\\D*)" 匹配时，输出"@EntityDetail"，未匹配到的字符串就不输出了
        String str2 = "@EntityDetail66(describe = \"白色的小猪\")";
        String str3 = "@EntityDetailuu(name = \"小狗\")"; //使用"@EntityDetail(\\D*)" 匹配时, 完整输出
        String str4 = "@EntityDetail(describe = \"黑色的小狗\")";
        List<String> strings = Lists.newArrayList();
        strings.add(str);
        strings.add(str2);
        strings.add(str3);
        strings.add(str4);

        String pattern = "@EntityDetail(\\D*)";
//        String pattern = "@EntityDetail\\((\\W*)";
        for (int i = 0; i < strings.size(); i++) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(strings.get(i));
            if (m.find()) {
                System.out.println("找到字符串：" + m.group(0)); //
            }
        }

    }

    private static void basicUse() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";

        // 表达式语法
        String pattern = "(\\D*)(\\d+)(.*)";
       // String pattern = "for";

        //Pattern 与 Matcher 都没有公有构造方法
        // 创建 Pattern（模式） 对象 : 将给定的正则表达式编译为模式。
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象  ： 创建一个匹配器，匹配给定的输入与此模式。
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("正则表达式" + r.pattern());
            // 字符串查找
            System.out.println("Found value 0: " + m.group(0));
            // 字符传替换
            System.out.println(m.replaceAll("test"));
        } else {
            System.out.println("NO MATCH");
        }
    }
}
