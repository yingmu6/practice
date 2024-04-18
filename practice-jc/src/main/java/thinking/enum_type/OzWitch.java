package thinking.enum_type;

/**
 * @author chensy
 * @date 2024/4/8
 */
public enum OzWitch {

    /**
     * 知识点：向enum中添加新方法
     */

    WEST("Miss Gulch，aka the Wiched Witch of the West"),

    NORTH("Glina，the Good Witch of the North"),

    EAST("Wicked Witch of the East，wearer of the Ruby " +
            "Slippers，crushed by Dorothy's house" ),

    SOUTH("Good by inference，but missing");

    private String description;

    private OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (OzWitch witch : OzWitch.values()) {
            System.out.println(witch + "：" + witch.getDescription());
        }
    }
}
