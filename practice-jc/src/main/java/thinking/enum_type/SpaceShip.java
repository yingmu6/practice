package thinking.enum_type;

/**
 * @author chensy
 * @date 2024/4/8
 */
public enum SpaceShip {

    /**
     * 知识点：覆盖enum的方法
     */
    SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;

    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }

    public static void main(String[] args) {
        for (SpaceShip s: values()) {
            System.out.println(s);
        }
    }
}
