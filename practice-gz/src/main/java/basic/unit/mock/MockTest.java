package basic.unit.mock;


import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author chensy
 * @date 2022/3/4
 */
public class MockTest {

    @Mock
    List mocksTest; //等价Mockito.mock(ArrayList.class)

    @Spy
    List spyTest;  //等价Mockito.spy(ArrayList.class)

    @Test
    public void basic() {
        List mocks = Mockito.mock(ArrayList.class);

        mocks.add("haha");
        System.out.println("列表数量：" + mocks.size());

        Mockito.verify(mocks).add("haha");            //校验mock列表是否添加了指定对象（不走真实调用）
        assertEquals(0, mocks.size());

        Mockito.when(mocks.size()).thenReturn(10);    //处理mock对象的属性值
        assertEquals(10, mocks.size());
        System.out.println("列表数量：" + mocks.size());
    }

    @Test
    public void spy_test() {
        List mocks = Mockito.spy(ArrayList.class);  //使用spy构建的mock对象，能自动改变对象的属性（默认走真实调用）
        mocks.add("haha");

        Mockito.verify(mocks).add("haha");
        assertEquals(1, mocks.size());
    }

    @Test
    public void captor_test() {
        List mocks = Mockito.mock(ArrayList.class);
        mocks.add("haha");

        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class); //使用capture能够捕获到mock对象的值
        Mockito.verify(mocks).add(arg.capture());

        assertEquals("haha", arg.getValue());

    }

    @Mock
    Map<String, String> nameMap = new HashMap<>();

    @InjectMocks
    Animal animal = new Animal();

    @Test
    public void inject_mock_test() {
        Mockito.when(nameMap.get("pig")).thenReturn("PeiQi");

        assertEquals("PeiQi", animal.getAnimalName("pig"));
    }

    @Test
    public void test_return() {
        Animal animal1 = Mockito.mock(Animal.class);
        Mockito.when(animal1.sayHello()).thenReturn("hh");
        System.out.println(animal1.sayHello());

        Animal animal2 = new Animal();
        System.out.println(animal2.sayHello());

        Animal animal3 = Mockito.mock(Animal.class);
        Mockito.when(animal3.getAnimalName("22")).thenReturn("hh44"); // 传入参数匹配上，才会返回值（符合预期，才返回mock值）

        System.out.println(animal3.getAnimalName("33"));
        System.out.println(animal3.getAnimalName("22"));

        Pig pig1 = new Pig();
        pig1.setName("小猪");
        Animal animal4 = Mockito.mock(Animal.class);
        Mockito.when(animal4.getPigCry(pig1)).thenReturn("小猪叫");

        Pig pig2 = new Pig();
        pig2.setName("大猪");
        Animal animal5 = Mockito.mock(Animal.class);
        Mockito.when(animal5.getPigCry(pig2)).thenReturn("大猪叫"); // 对象也可以做比较条件

        System.out.println(animal4.getPigCry(pig1));
        System.out.println(animal5.getPigCry(pig2));

        Mockito.when(animal5.getPigCry(Mockito.argThat(new ArgumentMatcher<Pig>() {
            @Override
            public boolean matches(Pig pig) {
                if (!(pig instanceof Pig)) {
                    return false;
                }
                return pig.getAge() == 1;
            }
        }))).thenReturn("猪1岁了");

        Pig pig3 = new Pig();
        pig3.setAge(1);
        System.out.println(animal5.getPigCry(pig3));
    }

}

/**
 * todo @csy 问题点：
 * 1）spy功能用途是什么？
 * 2）Mockito是怎么创建Mock对象以及操作对象的属性的？
 * 3）capture捕获值，怎么处理设置多个值的使用
 * 4）Mockito为啥报错
 *
 * <p>
 * https://waylau.com/mockito-quick-start/
 * Mock 测试就是在测试过程中，对于某些不容易构造（如 HttpServletRequest 必须在Servlet 容器中才能构造出来）或者不容易获取比较复杂的对象
 * （如 JDBC 中的ResultSet 对象），用一个虚拟的对象（Mock 对象）来创建以便测试的测试方法。
 */
