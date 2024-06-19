package thinking.array_relative;

import java.util.ArrayList;
import java.util.List;

/**
 * @author orange
 * @date 2024/5/28
 */
public class ArrayOfGenerics {

    /**
     * 知识点（16.5）：数组与泛型
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[]) la;
        ls[0] = new ArrayList<String>();
        // ls[1] = new ArrayList<Integer>(); //报类型不匹配的编译错误

        Object[] objects = ls;
        objects[1] = new ArrayList<Integer>();

        List<ContainerComparision.BerylliumSphere>[] spheres =
                (List<ContainerComparision.BerylliumSphere>[]) new List[10];
        for (int i = 0; i < spheres.length; i++) {
            spheres[i] = new ArrayList<ContainerComparision.BerylliumSphere>();
        }
    }
}
