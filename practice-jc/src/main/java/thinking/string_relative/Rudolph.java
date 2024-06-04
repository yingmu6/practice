package thinking.string_relative;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Rudolph {

    /**
     * 知识点（13.6.2）：创建正则表达式
     */
    public static void main(String[] args) {
        for (String pattern : new String[] {
                "Rudolph","[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*"}) {
            System.out.println("Rudolph".matches(pattern));
        }
    }
}
