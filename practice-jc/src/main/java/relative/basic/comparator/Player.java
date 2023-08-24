package relative.basic.comparator;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chensy
 * @date 2023/8/24
 */
@Getter
@Setter
public class Player implements Comparable<Player>{
    private int ranking;
    private String name;
    private int age;

    public Player() {}

    public Player(int ranking, String name, int age) {
        this.ranking = ranking;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Player otherPlayer) {
        return Integer.compare(getRanking(), otherPlayer.getRanking());
    }

    @Override
    public String toString() {
        return getName();
    }
}
