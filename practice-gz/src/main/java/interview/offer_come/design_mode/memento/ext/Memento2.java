package interview.offer_come.design_mode.memento.ext;

/**
 * @author chensy
 * @date 2024/7/10
 */
public class Memento2 { //@MsY-Done

    /**
     * 知识点：备忘录模式
     *
     * 知识点概括：
     * 1）备忘录模式的扩展，对每次变更的值按版本进行备忘，可回退到指定版本的值
     */

    private String value;

    public Memento2(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 新增场景：可回退到指定版本的备忘值
     */
    public static void main(String[] args) { //Done

        Original2 original = new Original2("张三2");
        Storage2 storage = new Storage2();
        // 设置第1个备忘对象值
        String version1 = storage.storeMemeto2(original.createMemento2()); //当原始对象的值有变更时，就创建一个与之对应的备忘对象，并缓存到Storage2对象中
        System.out.printf("original value: %s, version: %s \n", original.getValue(), version1);

        // 设置第2个备忘对象值
        original.setValue("李四2");
        String version2 = storage.storeMemeto2(original.createMemento2());
        System.out.printf("update value: %s, version: %s \n", original.getValue(), version2);

        // 设置第3个备忘对象值
        original.setValue("王五2");
        String version3 = storage.storeMemeto2(original.createMemento2());
        System.out.printf("update value: %s, version: %s \n", original.getValue(), version3);


        original.restoreMemento2(storage, version1); //恢复到指定版本对应的备忘对象存储的值
        System.out.printf("恢复到version1对应的值，restore value : %s \n", original.getValue());

        original.restoreMemento2(storage, version2);
        System.out.printf("恢复到version2对应的值，restore value : %s \n", original.getValue());

        original.restoreMemento2(storage, version3);
        System.out.printf("恢复到version3对应的值，restore value: %s \n", original.getValue());

        /**
         * 输出结果：
         * original value: 张三2, version: 202407_1
         * update value: 李四2, version: 202407_2
         * update value: 王五2, version: 202407_3
         * 恢复到version1对应的值，restore value : 张三2
         * 恢复到version2对应的值，restore value : 李四2
         * 恢复到version3对应的值，restore value: 王五2
         *
         * 结果分析：
         * 1）Original2是目标对象，当它的值初始化以及变更时，会将值存在备忘对象Memento2中，
         *    将Memento2存储到Storage2中时，将之与版本进行关联缓存起来，这样就能追踪到各个版本的值了
         *    回退的时候，只要给出版本号，就可找到对应的备忘值。类似git的版本恢复。
         */
    }
}
