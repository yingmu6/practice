package thinking.inner_class;

import thinking.inner_class.basic.Contents;
import thinking.inner_class.basic.Destination;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Parcel11 {

    protected static class ParcelContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class ParcelDestination implements Destination {
        private String label;

        public ParcelDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }

        public void fn() {
            System.out.println("ParcelDestination fn()");
        }

        static int x = 10;

        static class AnotherLevel { //ParcelDestination的静态内部类
            public void fn() {
                System.out.println("AnotherLevel fn()");
            }

            static int x = 10;

            public class Another2 {
                private String str;
                public Another2(String str) {
                    this.str = str;
                }
                public String getStr() {
                    return this.str;
                }
            }
        }
    }
}
