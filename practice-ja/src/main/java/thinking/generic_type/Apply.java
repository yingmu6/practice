package thinking.generic_type;
import java.lang.reflect.*;
import java.util.*;
import static net.mindview.util.Print.*;

/**
 * 知识点（15.17.2）：将一个方法应用于序列
 */

public class Apply { //@TkY-Doing
  public static <T, S extends Iterable<? extends T>>
  void apply(S seq, Method f, Object... args) {
    try {
      for(T t: seq)
        f.invoke(t, args);
    } catch(Exception e) {
      // Failures are programmer errors
      throw new RuntimeException(e);
    }
  }
}	

class Shape {
  public void rotate() { print(this + " rotate"); }
  public void resize(int newSize) {
    print(this + " resize " + newSize);
  }
}

class Square extends Shape {}

class FilledList<T> extends ArrayList<T> {
  public FilledList(Class<? extends T> type, int size) {
    try {
      for(int i = 0; i < size; i++)
        // Assumes default constructor:
        add(type.newInstance());
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
}	

class ApplyTest {

  public static void main(String[] args) throws Exception { //Doing @pause-07/11
    List<Shape> shapes = new ArrayList<Shape>();
    for(int i = 0; i < 10; i++)
      shapes.add(new Shape());
    Apply.apply(shapes, Shape.class.getMethod("rotate")); //依次调用集合中元素的rotate方法
    Apply.apply(shapes,
      Shape.class.getMethod("resize", int.class), 5); //依次调用集合中元素的resize方法
    List<Square> squares = new ArrayList<Square>();
    for(int i = 0; i < 10; i++)
      squares.add(new Square());
    Apply.apply(squares, Shape.class.getMethod("rotate"));
    Apply.apply(squares,
      Shape.class.getMethod("resize", int.class), 5);
	
    Apply.apply(new FilledList<Shape>(Shape.class, 10),
      Shape.class.getMethod("rotate"));
    Apply.apply(new FilledList<Shape>(Square.class, 10),
      Shape.class.getMethod("rotate"));

    SimpleQueue<Shape> shapeQ = new SimpleQueue<Shape>();
    for(int i = 0; i < 5; i++) {
      shapeQ.add(new Shape());
      shapeQ.add(new Square());
    }
    Apply.apply(shapeQ, Shape.class.getMethod("rotate"));

    /**
     * 输出结果：
     * thinking.generic_type.Shape@5fdef03a rotate
     * ........
     * thinking.generic_type.Shape@12bb4df8 rotate
     * thinking.generic_type.Shape@5fdef03a resize 5
     * .......
     * thinking.generic_type.Shape@12bb4df8 resize 5
     * thinking.generic_type.Square@7a7b0070 rotate
     * ......
     * thinking.generic_type.Square@6073f712 rotate
     * thinking.generic_type.Square@7a7b0070 resize 5
     * .....
     * thinking.generic_type.Square@6073f712 resize 5
     * thinking.generic_type.Shape@5f205aa rotate
     * .....
     * thinking.generic_type.Square@8807e25 rotate
     *
     * 结果分析：
     * 1）
     */
  }
}
