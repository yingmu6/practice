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
     * 5）理解正则表达式的步骤
     *   a）整理出表达式中元字符、范围字符、量词等
     *   b）参考正则表达式语法进行语法解读
     *   c）使用Mather、Pattern用例验证或在线验证
     *
     * 6）可以用圆括号()、方括号[] 框定范围
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
         * 规则说明：（区分出是系统预定义的字符，还是自定义字符【自定义字符会加上转义字符"\"】）
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
     * 场景3：测试指定的范围
     * 1）	[abc]	a, b, or c (simple class)
     * 2）	[^abc]	Any character except（除去） a, b, or c (negation 否定)
     * 3）	[a-zA-Z]	a through z or A through Z, inclusive包含 (range)
     * 4）	[a-d[m-p]]	a through d, or m through p: [a-dm-p] (union 联合)
     * 5）	[a-z&&[def]]	d, e, or f (intersection 交集)
     * 6）	[a-z&&[^bc]]	a through z, except for b and c: [ad-z] (subtraction 子集)
     * 7）	[a-z&&[^m-p]]	a through z, and not m through p: [a-lq-z](subtraction)
     *
     * 特别注意：若没有加上量词修饰，则只匹配一个字符，若要匹配多个字符，要加上量词
     */
    @Test
    public void test_special_range() {
        // [abc]：在指定字符集中（只包含a、b、c字符）
        System.out.println("--------分隔线1-------------");
        System.out.println(Pattern.matches("[abc]", "abcd"));//false（超过一个字符，不匹配）
        System.out.println(Pattern.matches("[abc]", "a"));//true
        System.out.println(Pattern.matches("[abc]*", "ab"));//true (匹配多个字符，要加上量词)

        // [^abc]：除去指定的字符集（除去a、b、c字符）
        System.out.println("--------分隔线2-------------");
        System.out.println(Pattern.matches("[^abc]", "a"));//false
        System.out.println(Pattern.matches("[^abc]", "d"));//true

        // [a-zA-Z]：包含指定的范围（包含a到z，或A到Z字符）
        System.out.println("--------分隔线3-------------");
        System.out.println(Pattern.matches("[a-zA-Z]", "a"));//true
        System.out.println(Pattern.matches("[a-zA-Z]", "A"));//true
        System.out.println(Pattern.matches("[a-zA-Z]", "5"));//false

        // [a-d[m-p]]：包含指定的范围（包含a到z，或m到p字符）
        System.out.println("--------分隔线4-------------");
        System.out.println(Pattern.matches("[a-d[m-p]]", "a"));//true
        System.out.println(Pattern.matches("[a-d[m-p]]", "m"));//true
        System.out.println(Pattern.matches("[a-d[m-p]]", "am"));//false

        // [a-z&&[def]]：包含指定的范围（包含a到z与[def]的交集，即最终的字符为d、e或f）
        System.out.println("--------分隔线5-------------");
        System.out.println(Pattern.matches("[a-z&&[def]]", "d"));//true
        System.out.println(Pattern.matches("[a-z&&[def]]", "e"));//true
        System.out.println(Pattern.matches("[a-z&&[def]]", "a"));//false

        // [a-z&&[^bc]]：包含指定的范围（包含a到z且不为b或c的交集，即最终的字符为a到z字符，但是要排除b、c）
        System.out.println("--------分隔线6-------------");
        System.out.println(Pattern.matches("[a-z&&[^bc]]", "b"));//false
        System.out.println(Pattern.matches("[a-z&&[^bc]]", "c"));//false
        System.out.println(Pattern.matches("[a-z&&[^bc]]", "a"));//true

        // [a-z&&[^m-p]]：包含指定的范围（包含a到z且不在m到p的交集，即最终的字符为a到z字符，但要排除m到p的字符）
        System.out.println("--------分隔线7-------------");
        System.out.println(Pattern.matches("[a-z&&[^m-p]]", "m"));//false
        System.out.println(Pattern.matches("[a-z&&[^m-p]]", "a"));//true
    }

    /**
     * 场景4：测试量词
     * 1) X?	X occurs once or not at all （出现一次或不出现）
     * 2) X+	X occurs once or more times （出现一次或者多次）
     * 3) X*	X occurs zero or more times （出现0次或者多次）
     * 4) X{n}	X occurs n times only       （出现指定的次数n）
     * 5) X{n,}	X occurs n or more times    （出现n次或n次以上，即 n <= t ）
     * 6) X{y,z}	X occurs at least y times but less than z times（出现至少y次，但小于z次，即 y <= t <= z）
     */
    @Test
    public void test_Quantifiers() {

        System.out.println("--------分隔线1-------------");
        System.out.println(Pattern.matches("[amn]?", "a"));//true 字符出现1次
        System.out.println(Pattern.matches("[amn]?", "m"));//true 字符出现0次

        System.out.println("--------分隔线2-------------");
        System.out.println(Pattern.matches("[amn]+", "a"));//true
        System.out.println(Pattern.matches("[amn]+", "ammn"));//true 字符出现一次或多次
        System.out.println(Pattern.matches("[amn]+", "fd"));//false

        System.out.println("--------分隔线3-------------");
        System.out.println(Pattern.matches("[amn]*", "a"));//true
        System.out.println(Pattern.matches("[amn]*", "ammn"));//true 字符出现0次或多次

        System.out.println("--------分隔线4-------------");
        System.out.println(Pattern.matches("[amn]{2}", "a"));//false
        System.out.println(Pattern.matches("[amn]{2}", "ammn"));//false 字符出现指定的次数
        System.out.println(Pattern.matches("[amn]{2}", "an"));//true

        System.out.println("--------分隔线5-------------");
        System.out.println(Pattern.matches("[amn]{2,}", "a"));//false
        System.out.println(Pattern.matches("[amn]{2,}", "ammn"));//true 字符出现至少n次以上
        System.out.println(Pattern.matches("[amn]{2,}", "an"));//true

        System.out.println("--------分隔线6-------------");
        System.out.println(Pattern.matches("[amn]{2,4}", "a"));//false
        System.out.println(Pattern.matches("[amn]{2,4}", "ammn"));//true 字符出现至少2次，但小于等于4次
        System.out.println(Pattern.matches("[amn]{2,4}", "ann"));//true
        System.out.println(Pattern.matches("[amn]{2,4}", "annmm"));//false
    }

    /**
     * 场景5：测试元字符
     * 1) .	    Any character (may or may not match terminator) //任意字符
     * 2) \d	Any digits, short of [0-9]                      //任意数字字符
     * 3) \D	Any non-digit, short for [^0-9]                 //任意非数字字符
     * 4) \s	Any whitespace character, short for [\t\n\x0B\f\r] //任意空白字符
     * 5) \S	Any non-whitespace character, short for [^\s]      //任意非空白字符
     * 6) \w	Any word character, short for [a-zA-Z_0-9]         //任意字符
     * 7) \W	Any non-word character, short for [^\w]            //任意非字符
     * 8) \b	A word boundary（边界）
     * 9) \B	A non word boundary
     *
     * 注明：如上元字符，大写表示"非"的含义（可以理解为"大非"）
     *
     */
    @Test
    public void test_metacharacters() {
        System.out.println("--------分隔线1-------------");
        System.out.println(Pattern.matches("\\d", "abc"));//false (non-digit)
        System.out.println(Pattern.matches("\\d", "1"));//true (digit and comes once)
        System.out.println(Pattern.matches("\\d*", "4443"));//true ("[\\d]*" 与 "\\d" 两种写法相同)
        System.out.println(Pattern.matches("\\d", "323abc"));//false (digit and char)

        System.out.println("--------分隔线2-------------");
        System.out.println(Pattern.matches("\\D", "abc"));//false (non-digit but comes more than once)
        System.out.println(Pattern.matches("\\D", "1"));//false (digit)
        System.out.println(Pattern.matches("\\D", "4443"));//false (digit)
        System.out.println(Pattern.matches("\\D", "323abc"));//false (digit and char)
        System.out.println(Pattern.matches("\\D", "m"));//true (non-digit and comes once)

        System.out.println("--------分隔线3-------------");
        System.out.println(Pattern.matches("\\D*", "mak"));//true (non-digit and may come 0 or more times)
    }

    /**
     * 场景N：几个特殊正则表达式测试
     * a）[\\-._0-9a-zA-Z]+
     * b）[*,\\-._0-9a-zA-Z]+
     * c）[a-zA-Z][0-9a-zA-Z]*
     */
    @Test
    public void test_special_express() {

        /**
         * [\\-._0-9a-zA-Z]+
         * 解读：出现一个或多个[...]中的字符，[...]里面的字符包含：-符号、.字符、0到9 或 a到z 或 A-Z中的字符
         */
        System.out.println("--------分隔线1-------------");
        System.out.println(Pattern.matches("[\\-._0-9a-zA-Z]+", "-._1")); //true（字符在指定范围中出现一次或多次）
        System.out.println(Pattern.matches("[\\-._0-9a-zA-Z]+", "---")); //true
        System.out.println(Pattern.matches("[\\-._0-9a-zA-Z]+", ".")); //true
        System.out.println(Pattern.matches("[\\-._0-9a-zA-Z]+", "___")); //true
        System.out.println(Pattern.matches("[\\-._0-9a-zA-Z]+", "|")); //false （字符没有在指定范围中）

        /**
         * [*,\\-._0-9a-zA-Z]+
         * 解读：出现一个或多个[...]中的字符，[...]里面的字符包含：'*'、','、'-'、'.'、0到9 或 a到z 或 A-Z中的字符
         */
        System.out.println("--------分隔线2-------------");
        System.out.println(Pattern.matches("[*,\\-._0-9a-zA-Z]+", "***,,,---...___")); //true
        System.out.println(Pattern.matches("[*,\\-._0-9a-zA-Z]+", "***"));             //true
        System.out.println(Pattern.matches("[*,\\-._0-9a-zA-Z]+", ",,,"));             //true
        System.out.println(Pattern.matches("[*,\\-._0-9a-zA-Z]+", "___"));             //true

        /**
         * [a-zA-Z][0-9a-zA-Z]*
         * 解读：第一个字符是[a-zA-Z]，后面的字符出现0个或多个[0-9a-zA-Z]
         */
        System.out.println("--------分隔线3-------------");
        System.out.println(Pattern.matches("[a-zA-Z][0-9a-zA-Z]*", "a45x")); //true
        System.out.println(Pattern.matches("[a-zA-Z][0-9a-zA-Z]*", "3aaa")); //false
        System.out.println(Pattern.matches("[a-zA-Z][0-9a-zA-Z]*", "a"));    //true

        /**
         * "\\s*[|;]+\\s*"
         */
        System.out.println("--------分隔线4-------------");
        System.out.println(Pattern.matches("\\s*[|;]+\\s*", "||| ")); //true
        System.out.println(Pattern.matches("\\s*[|;]+\\s*", " ; ")); //true
        System.out.println(Pattern.matches("(\\s*[|;]+\\s*)", " ; ")); //true
        System.out.println(Pattern.matches("[/\\s*[|;]+\\s]*", "/\t\n\r;")); //true， 匹配'/'符号，非打印字符，如'\t'，'\n'，匹配的正则表达式中没有，匹配的字符串中有，也能匹配上

        /**
         * [a-zA-Z][0-9a-zA-Z]*
         * 解读：第一字符为a到z或A到Z，后面的字符可以出现0个或多个 0到9 或 a到z 或A到Z
         */
        System.out.println("--------分隔线5-------------");
        System.out.println(Pattern.matches("[a-zA-Z][0-9a-zA-Z]*", "a11aaZ")); //true
        System.out.println(Pattern.matches("[a-zA-Z][0-9a-zA-Z]*", "211aaZ")); //false
        System.out.println(Pattern.matches("[a-zA-Z][0-9a-zA-Z]*", "a")); //true
        System.out.println(Pattern.matches("[a-zA-Z][0-9a-zA-Z]*", "")); //false  按照正则表达式 [a-zA-Z] 必须出现一个字符
    }

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

    @Test
    public void basicUse() {
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
