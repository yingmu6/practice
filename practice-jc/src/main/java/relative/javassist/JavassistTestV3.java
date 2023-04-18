package relative.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

/**
 * @Author chenSy
 * @Date 2023/04/17 16:34
 * @Description
 */
public class JavassistTestV3 {

    /**
     * 场景1：写一个可运行的用例（在可运行的前提下，解决JavassistTest、JavassistTestV2的运行问题）
     * 官网例子可用，https://www.javassist.org/tutorial/tutorial.html
     */
    @Test
    public void basicUse() throws Exception {
        ClassPool cp = ClassPool.getDefault();
//        cp.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader())); //此处可以不用

        CtClass cc = cp.get("relative.javassist.Hello"); //此处需要全路径，使用"Hello"还找不到类，可做分析
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.sayHi():\"); }");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }
}
