package thinking.generic_type;

import java.util.*;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class LostInformation {

    /**
     * 知识点（15.7）：擦除的神秘之处
     */
    static class Frob {}
    static class Fnorkle {}
    static class Quark<Q> {}
    static class Particle<POSITION, MOMENTUM> {}

    public static void main(String[] args) {
        List<Frob> list = new ArrayList<Frob>();
        Map<Frob, Fnorkle> map = new HashMap<Frob, Fnorkle>();
        Quark<Fnorkle> quark = new Quark<Fnorkle>();
        Particle<Long, Double> p = new Particle<Long, Double>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }
}
