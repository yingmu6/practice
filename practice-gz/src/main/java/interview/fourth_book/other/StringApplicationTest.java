package interview.fourth_book.other;

import org.junit.Test;

import java.util.*;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class StringApplicationTest { //字符串相关应用测试

    /**
     * 场景1：把一个数组里的数组合全部列出，比如1和2列出来为1，2，12，21
     */
    @Test
    public void basicUse1() {
        String[] array = new String[] {
                "1", "2", "3", "4"
        };

        listAll(Arrays.asList(array), "");
    }

    /**
     * 场景2：用递归的方法，计算菲波那切数列的通项f(n)，已知f1=1，f2=1，以后每项都是前两项的和
     */
    @Test
    public void basicUse2() {
        Scanner cin = new Scanner(System.in);
        long a = cin.nextLong();
        System.out.println(fibonacci(a));
        System.out.println("共递归调用了" + k + "次");
    }

    /**
     * 场景3：一个字符串中可能包含a~z中的多个字符，如有重复，如String data = "aavzcadfdsdhshgWasdfasdfdddaaa"，
     *      求出现次数最多的那个字母及次数，如有多个重复的则都求出
     */
    @Test
    public void basicUse3() {
        String input = "aavzcadfdsdhshgWasdfasdfdddaaa";
        char[] chars = input.toCharArray();
        ArrayList lists = new ArrayList();
        TreeSet set = new TreeSet();
        for (int i = 0; i < chars.length; i++) {
            lists.add(String.valueOf(chars[i]));
            set.add(String.valueOf(chars[i]));
        }

        System.out.println(set);
        Collections.sort(lists);
        System.out.println(lists);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            sb.append(lists.get(i));
        }

        input = sb.toString();
        System.out.println(input);
        int max = 0;
        String maxString = "";
        ArrayList maxList = new ArrayList();

        Iterator its = set.iterator();
        while (its.hasNext()) {
            String os = (String) its.next();
            int begin = input.indexOf(os);
            int end = input.lastIndexOf(os);
            int value = end - begin + 1;
            if (value > max) {
                max = value;
                maxString = os;
                maxList.add(os);
            } else if (value == max) {
                maxList.add(os);
            }
        }

        int index = 0;
        for (int i = 0; i < maxList.size(); i++) {
            if (maxList.get(i).equals(maxString)) {
                index = i;
                break;
            }
        }

        System.out.println("max data");
        for (int i = 0; i < maxList.size(); i++) {
            System.out.println(maxList.get(i) + "");
        }

        System.out.println();
        System.out.println("max" + max);
    }

    /**
     * 场景4：利用1，2，2，3，4这5个数字，用Java写一个main函数，打印出所有不同的排列，如12234，21234，要求打印出来的不能有重复
     */
    static int count = 0;

    @Test
    public void basicUse4() {
        String s = "1223";
        String s2 = "1232";
        int t = 1 & 0;
        System.out.println(t);

        int index[] = new int[s.length()];
        for (int i = 0; i < s2.length(); i++) {
           index[i] = s2.indexOf(s2.charAt(i));
        }

        pailie(s, "");
    }

    /**
     * 场景5：利用筛选法查找100以内的素数
     */
    @Test
    public void basicUse5() {
        int a[] = new int[101];
        int i, j;
        for (i = 0; i < 101; i++) {
            a[i] = 1;
        }

        for (i = 2; i < 101; i++) {
            if (a[i] != 0) {
                for(j = i + i; j < 101;) {
                    if (j % i == 0) {
                        a[j] = 0;
                        j = j + 1;
                    }
                }
            }
        }

        for (i = 2; i < 101; i++) {
            if (a[i] != 0) {
                System.out.println(i);
            }
        }
    }

    static void pailie(String s, String p) {
        if (s.length() < 1) {
            count++;
        } else {
            int index[] = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                index[i] = s.indexOf(s.charAt(i));
            }

            for (int i = 0; i < s.length(); i++) {
                if (i == index[i]) {
                    pailie(s.substring(1), p + s.substring(0, 1));
                }

                s = s.substring(1) + s.substring(0, 1);
            }
        }
    }

    public static long fibonacci(long m) {
        if (m == 0 || m == 1) {
            k++;
            return m;
        } else {
            return fibonacci(m - 1) + fibonacci(m - 2);
        }
    }

    public static int k = 0;

    public static void listAll(List candidate, String prefix) {
        System.out.println(prefix);

        for (int i = 0; i < candidate.size(); i++) {
            List temp = new LinkedList(candidate);
            listAll(temp, prefix + temp.remove(i));
        }
    }
}
