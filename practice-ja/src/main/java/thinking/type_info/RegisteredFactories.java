package thinking.type_info;
// Registering Class Factories in the base class.
import thinking.type_info.factory.Factory;

import java.util.*;


/**
 * 知识点（14.4）：注册工厂
 *
 * 问题点答疑：
 * 1）为什么叫作注册工厂，是要表明一种设计思想吗？
 */

class Part {
  public String toString() {
    return getClass().getSimpleName();
  }
  static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();

  static { //静态块，做初始化
    // Collections.addAll() gives an "unchecked generic
    // array creation ... for varargs parameter" warning.
    partFactories.add(new FuelFilter.Factory()); //创建静态内部类实例
    partFactories.add(new AirFilter.Factory());
    partFactories.add(new CabinAirFilter.Factory());
    partFactories.add(new OilFilter.Factory());
    partFactories.add(new FanBelt.Factory());
    partFactories.add(new PowerSteeringBelt.Factory());
    partFactories.add(new GeneratorBelt.Factory());
  }
  private static Random rand = new Random(47);
  public static Part createRandom() {
    int n = rand.nextInt(partFactories.size());
    return partFactories.get(n).create();
  }
}

class Filter extends Part {}

class FuelFilter extends Filter {
  // Create a Class Factory for each specific type:
  public static class Factory //静态内部类
  implements thinking.type_info.factory.Factory<FuelFilter> {
    public FuelFilter create() { return new FuelFilter(); }
  }
}

class AirFilter extends Filter {
  public static class Factory
  implements thinking.type_info.factory.Factory<AirFilter> {
    public AirFilter create() { return new AirFilter(); }
  }
}	

class CabinAirFilter extends Filter {
  public static class Factory
  implements thinking.type_info.factory.Factory<CabinAirFilter> {
    public CabinAirFilter create() {
      return new CabinAirFilter();
    }
  }
}

class OilFilter extends Filter {
  public static class Factory
  implements thinking.type_info.factory.Factory<OilFilter> {
    public OilFilter create() { return new OilFilter(); }
  }
}	

class Belt extends Part {}

class FanBelt extends Belt {
  public static class Factory
  implements thinking.type_info.factory.Factory<FanBelt> {
    public FanBelt create() { return new FanBelt(); }
  }
}

class GeneratorBelt extends Belt {
  public static class Factory
  implements thinking.type_info.factory.Factory<GeneratorBelt> {
    public GeneratorBelt create() {
      return new GeneratorBelt();
    }
  }
}	

class PowerSteeringBelt extends Belt {
  public static class Factory
  implements thinking.type_info.factory.Factory<PowerSteeringBelt> {
    public PowerSteeringBelt create() {
      return new PowerSteeringBelt();
    }
  }
}	

public class RegisteredFactories {
  public static void main(String[] args) { //Done
    for(int i = 0; i < 10; i++)
      System.out.println(Part.createRandom()); //会调用Part的toString()输出信息
  }

  /**
   * 输出结果：
   * GeneratorBelt
   * CabinAirFilter
   * GeneratorBelt
   * AirFilter
   * PowerSteeringBelt
   * CabinAirFilter
   * FuelFilter
   * PowerSteeringBelt
   * PowerSteeringBelt
   * FuelFilter
   *
   * 结果分析：
   * 1）在父类Part会通过静态块进行初始化，并重写toString()方法输出类的信息
   *
   */
}