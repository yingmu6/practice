package thinking.type_info.pets;
import java.util.*;

public class ForNameCreator extends PetCreator {
  private static List<Class<? extends Pet>> types =
    new ArrayList<Class<? extends Pet>>();
  // Types that you want to be randomly created:
  private static String[] typeNames = {
    "thinking.type_info.pets.Mutt",
    "thinking.type_info.pets.Pug",
    "thinking.type_info.pets.EgyptianMau",
    "thinking.type_info.pets.Manx",
    "thinking.type_info.pets.Cymric",
    "thinking.type_info.pets.Rat",
    "thinking.type_info.pets.Mouse",
    "thinking.type_info.pets.Hamster"
  };	
  @SuppressWarnings("unchecked")
  private static void loader() {
    try {
      for(String name : typeNames)
        types.add(
          (Class<? extends Pet>)Class.forName(name)); //使用forName方式获取Class对象
    } catch(ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  static { loader(); } //使用static块调用static方法，进行类加载时的初始化
  public List<Class<? extends Pet>> types() {return types;}
}
