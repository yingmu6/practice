package basic.solution;

import com.alibaba.fastjson.JSON;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * 解析json字符串
 *
 * @author chensy
 * @date 2022/5/11
 */
public class ParseJsonStringTest {

    public static void main(String[] args) {
        ParseJsonStringTest test = new ParseJsonStringTest();
        String content = test.buildOperator("101", "张三");
        System.out.println("构建的内容：" + content);

        System.out.println("解析的内容1：" + test.parseOptPersonId(content));
        System.out.println("解析的内容2：" + test.parseOptPersonName(content));
    }

    // 构建操作人信息
    private String buildOperator(String personId, String personName) {
        return OperatorInfo.builder() //优点：使用建造者方式构建对象，形象易懂，还能连续设置值，精简
                .optPersonId(personId)
                .optPersonName(personName)
                .build()
                .buildOperatorInfo();
    }

    // 解析出操作人id
    private String parseOptPersonId(String operatorInfo) {
        return OperatorInfo.builder()
                .build()
                .parseOperatorInfo(operatorInfo)
                .getOptPersonId();
    }

    // 解析出操作人id
    private String parseOptPersonName(String operatorInfo) {
        return OperatorInfo.builder()
                .build()
                .parseOperatorInfo(operatorInfo)
                .getOptPersonName();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor  //优点：使用工具类，避免写get/set方法以及构造方法
    static class OperatorInfo {

        /**
         * 操作人id
         */
        private String optPersonId;

        /**
         * 操作人昵称
         */
        private String optPersonName;

        // 构建操作人信息
        public String buildOperatorInfo() {
            return JSON.toJSONString(this);
        }

        // 解析操作人信息
        public OperatorInfo parseOperatorInfo(String content) {
            try {
                OperatorInfo operatorInfo = JSON.parseObject(content, OperatorInfo.class); //优点：json的内容：用具体的对象约束，而不是用字符串的key表示，面对对象编程
                this.optPersonId = operatorInfo.getOptPersonId();
                this.optPersonName = operatorInfo.getOptPersonName();
            } catch (Exception e) {
                // 忽略解析异常
                this.optPersonId = StringUtils.EMPTY; //优点：充分用上了this访问内部成员变量
                this.optPersonName = StringUtils.EMPTY;
            }
            return this;
        }
    }
}


