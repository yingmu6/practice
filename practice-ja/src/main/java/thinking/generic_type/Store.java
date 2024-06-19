package thinking.generic_type;

import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class Store extends ArrayList<Store.Aisle> {

    /**
     * 知识点（15.6）：构建复杂模型
     */

    static class Product {
        private final int id;
        private String description;
        private double price;
        public Product(int IDnumber, String descr, double price) {
            id = IDnumber;
            description = descr;
            this.price = price;
            System.out.println(toString());
        }

        public String toString() {
            return id + "：" + description + "，price：$" + price;
        }

        public void priceChange(double change) {
            price += change;
        }

        public static Generator<Product> generator =
                new Generator<Product>() {
                    private Random rand = new Random(47);
                    @Override
                    public Product next() {
                        return new Product(rand.nextInt(1000), "Test",
                                Math.round(rand.nextDouble() * 1000.0) + 0.99);
                    }
                };
    }

    static class Shelf extends ArrayList<Product> {
        public Shelf(int nProducts) {
            Generators.fill(this, Product.generator, nProducts);
        }
    }

    static class Aisle extends ArrayList<Shelf> {
        public Aisle(int nShelves, int nProducts) {
            for (int i = 0; i < nShelves; i++) {
                add(new Shelf(nProducts));
            }
        }
    }

    static class CheckoutStand {}
    static class Office {}


    private ArrayList<CheckoutStand> checkouts =
            new ArrayList<>();
    private Office office = new Office();
    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves, nProducts));
        }
    }
    public String toString() {
        StringBuffer result = new StringBuffer();
        for(Aisle a : this) {
            for (Shelf s : a) {
                for (Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }
}
