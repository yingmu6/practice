package thinking.inner_class;

import thinking.inner_class.basic.Destination;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Parcel10 {
    public Destination destination(final String dest, final float price) {
        return new Destination() {
            private int cost;
            {
                cost = Math.round(price); //能访问外部类传入的参数
                if (cost > 100) {
                    System.out.println("Over budget!");
                }
            }

            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }
}
