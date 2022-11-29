package com.csy.design.factory.simple;

/**
 * @author : chensy
 * Date : 2020/11/17 下午1:10
 */
public class ProductFactory { //负责对象的创建，根据不同的类型，实例化对象
    public static Product getProduct(String type) throws Exception{
       switch (type) {
           case "tv":
               return new Tv();
           case "car":
               return new Car();
           default:
               throw new Exception("类型异常");
       }
       // 可以根据反射机制，来进行优化，可以动态的新增其它类型的实例如：
        // Product product = (Product) Class.forName(className).newInstance();
    }
}
