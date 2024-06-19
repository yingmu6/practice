package relative.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;
import javassist.LoaderClassPath;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : chensy
 * Date : 2020/11/12 上午9:49
 */
public class JavassistTestV2 {

    private static ClassPool CLASS_POOL;

    private static CtClass ANIMAL_CT_CLASS;

    private static CtClass FRUIT_CT_CLASS;

    @Before
    public void init() throws Exception{
        CLASS_POOL = ClassPool.getDefault();
        CLASS_POOL.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader())); //方式二
        ANIMAL_CT_CLASS = CLASS_POOL.get(Animal.class.getName());
        FRUIT_CT_CLASS = CLASS_POOL.get(Fruit.class.getName());
    }

    /**
     * 修改已有的方法
     *   可以通过对象调用增强的方法
     */
    @Test
    public void editMethod() throws Exception{
        CtMethod ctMethod = FRUIT_CT_CLASS.getDeclaredMethod("showInfo");
        ctMethod.insertBefore("System.out.println(\"增强代码 !!!\");");
        Loader classLoader = new Loader(CLASS_POOL);
        Fruit fruit = (Fruit) classLoader.loadClass("relative.javassist.Fruit").newInstance();
        fruit.showInfo();
    }
}
