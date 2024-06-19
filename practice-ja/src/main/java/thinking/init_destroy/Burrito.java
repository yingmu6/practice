package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class Burrito { //@TkY-Doing

    /**
     * 知识点（5.9）：
     *
     * 知识点概括：
     * 1）
     */
    Spiciness degree;
    public Burrito(Spiciness degree) {
        this.degree = degree;
    }
    public void describe() {
        System.out.print("This burrito is ");
        switch (degree) {
            case NOT:
                System.out.println("not spicy at all. ");
                break;
            case MILD:
            case MEDIUM:
                System.out.println("a little hot. ");
                break;
            case HOT:
            case FLAMING:
            default:
                System.out.println("maybe too hot. ");
        }
    }

    public static void main(String[] args) {
        Burrito
                plain = new Burrito(Spiciness.NOT),
                greenChile = new Burrito(Spiciness.MEDIUM),
                jalapeno = new Burrito(Spiciness.HOT); //创建同类的多个对象时，可用逗号分隔声明
        plain.describe();
        greenChile.describe();
        jalapeno.describe();

        /**
         * 输出结果：
         * This burrito is not spicy at all.
         * This burrito is a little hot.
         * This burrito is maybe too hot.
         *
         * 结果分析：
         * 1）构建Burrito对象时，设置成员变量Spiciness的值，并在describe()方法中
         *    根据枚举值进行判断并对应执行
         */
    }
}
