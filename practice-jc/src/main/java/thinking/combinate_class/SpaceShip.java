package thinking.combinate_class;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class SpaceShip extends SpaceShipControls {

    /**
     * 知识点（7.3）：使用继承
     */

    private String name;
    public SpaceShip(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        SpaceShip protector = new SpaceShip("NSEA Protector");
        protector.forward(100);
    }
}
