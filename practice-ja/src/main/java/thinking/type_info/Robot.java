package thinking.type_info;
import java.util.*;
import net.mindview.util.*;

public interface Robot {
  String name();
  String model();
  List<Operation> operations();

  class Test { //接口中的内部类
    public static void test(Robot r) {
      if(r instanceof Null) {
        System.out.println("[Null Robot]");
      }

      System.out.println("Robot name: " + r.name());
      System.out.println("Robot model: " + r.model());

      for(Operation operation : r.operations()) {
        System.out.println(operation.description());
        operation.command();
      }
    }
  }
}
