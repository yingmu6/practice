package thinking.control_flow;

import java.util.Random;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author orange
 * @date 2024/6/3
 */
public class VowelsAndConsonants {

    /**
     * 知识点（4.8）：switch
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        for(int i = 0; i < 100; i++) {
            int c = rand.nextInt(26) + 'a';
            printnb((char)c + "，" + c + "：");
            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u': print("vowel");
                          break;
                case 'y':
                case 'w': print("Sometimes a vowel");
                          break;
                default: print("consonant");
            }
        }
    }
}
