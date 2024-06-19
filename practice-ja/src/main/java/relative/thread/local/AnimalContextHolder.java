package relative.thread.local;

/**
 * 使用ThreadLocal做上下文数据持有者
 *
 * @author chensy
 * @date 2021/11/7
 */
public class AnimalContextHolder {

    private static ThreadLocal<AnimalClass> ANIMAL_CONTEXT = new ThreadLocal();

    public static int TEST;

    public static void setContext(AnimalClass animalClass) {
        if (animalClass == null) {
            return;
        }
        ANIMAL_CONTEXT.set(animalClass);
    }

    public static AnimalClass getContext() {
        AnimalClass animalClass = ANIMAL_CONTEXT.get();
        if (animalClass == null) {
            animalClass = new AnimalClass();
            ANIMAL_CONTEXT.set(animalClass);
        }
        return ANIMAL_CONTEXT.get();
    }

    public static void removeContext() {
        ANIMAL_CONTEXT.remove();
    }
}
