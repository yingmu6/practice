package interview.offer_come.basic.generic_type;

import java.util.Date;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class GeneralMethod { //泛型方法
    public <T> void generalMethod(T ... inputArray) {
        for (T element : inputArray) {
            if (element instanceof Integer) {
                System.out.println("处理Integer类型数据中...");
            } else if (element instanceof  String) {
                System.out.println("处理String类型数据中...");
            } else if (element instanceof  Double) {
                System.out.println("处理Double类型数据中...");
            } else if (element instanceof  Float) {
                System.out.println("处理Float类型数据中...");
            } else if (element instanceof  Long) {
                System.out.println("处理Long类型数据中...");
            } else if (element instanceof Boolean) {
                System.out.println("处理Boolean类型数据中...");
            } else if (element instanceof Date) {
                System.out.println("处理Date类型数据中...");
            } else if (element instanceof GeneralMethod) {
                System.out.println("处理GeneralMethod类型数据中...");
            }
        }
    }

}
