package thinking.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chensy
 * @date 2024/5/3
 */
public class UseCaseTracker { //@TkY-Doing

    /**
     * 知识点（20.2）：编写注解处理器
     */
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) {
            UseCase uc = m.getDeclaredAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found Use Case：" + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }

        for (int i : useCases) {
            System.out.println("Warning：Missing use case-" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);

        /**
         * 输出结果：（输出结果可能会变）
         * Found Use Case：49 New passwords can't equal previously used ones
         * Found Use Case：48 no description
         * Found Use Case：47 Passwords must contain at least one numeric
         * Warning：Missing use case-50
         *
         * 结果分析：
         */
    }
}
