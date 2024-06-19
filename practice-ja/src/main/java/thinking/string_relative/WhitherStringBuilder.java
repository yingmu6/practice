package thinking.string_relative;

/**
 * @author orange
 * @date 2024/6/4
 */
public class WhitherStringBuilder {

    /**
     * 知识点（13.2）：StringBuilder
     */
    public String implicit(String[] fields) {
        String result = "";
        for (int i = 0; i < fields.length; i++) {
            result += fields[i];
        }
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            result.append(fields[i]);
        }
        return result.toString();
    }

    /**
     * 使用javap -c WhitherStringBuilder查看对应的字节码
     */
}
