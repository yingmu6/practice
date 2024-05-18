package thinking.generic_type;

import net.mindview.util.Generator;

import java.util.*;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class BankTeller {

    /**
     * 知识点（15.5）：匿名内部类
     */

    static class Customer {
        private static long counter = 1;
        private final long id = counter++;
        private Customer() {}
        public String toString() { return "Customer " + id; }
        public static Generator<Customer> generator() {
            return new Generator<Customer>() {
                @Override
                public Customer next() {
                    return new Customer();
                }
            };
        }
    }

    static class Teller {
        private static long counter = 1;
        private final long id = counter++;
        private Teller() {}
        public String toString() { return "Teller " + id; }
        public static Generator<Teller> generator =
                new Generator<Teller>() {
                    @Override
                    public Teller next() {
                        return new Teller();
                    }
                };
    }

    public static void serve(Teller t, Customer c) {
        System.out.println(t + " serves " + c);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<Customer> line = new LinkedList<>();
        Generators.fill(line, Customer.generator(), 15);
        List<Teller> tellers = new ArrayList<>();
        Generators.fill(tellers, Teller.generator, 4);
        for (Customer c : line) {
            serve(tellers.get(rand.nextInt(tellers.size())), c);
        }
    }
}
