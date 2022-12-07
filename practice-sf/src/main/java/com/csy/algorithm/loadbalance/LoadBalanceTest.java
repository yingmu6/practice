package com.csy.algorithm.loadbalance;

/**
 * 随机算法测试
 *
 * @Author chenSy
 * @Date 2022/12/07 13:11
 * @Description
 */
public class LoadBalanceTest {
    public static void main(String[] args) {

        RandomLoadBalance randomLoadBalance = new RandomLoadBalance();
        // 相同权重
        for (int i = 0; i < 10; i++) {
            System.out.println("随机算法获取的序号（相等权重）：" + randomLoadBalance.select(10, 100));
        }

        // 较小权重
        for (int i = 0; i < 10; i++) {
            System.out.println("随机算法获取的序号（较小权重）：" + randomLoadBalance.select(10, 50));
        }

        // 较大权重
        for (int i = 0; i < 10; i++) {
            System.out.println("随机算法获取的序号（较大权重）：" + randomLoadBalance.select(10, 200));
        }
    }
}
