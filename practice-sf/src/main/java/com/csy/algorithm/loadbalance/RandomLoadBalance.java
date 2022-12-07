package com.csy.algorithm.loadbalance;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机的负载均衡算法
 *
 * @Author chenSy
 * @Date 2022/12/07 13:53
 * @Description
 */
public class RandomLoadBalance implements LoadBalance{

    private static final int DEFAULT_WEIGHT = 100;

    @Override
    public int select(int invokerNum, int weight) {

        // 因为都是单容器部署，所以感知不到其它容器的权重值，所以就按一个约定的数值，判断权重是否相同
        boolean sameWeight = true;
        if (weight != DEFAULT_WEIGHT) {
            sameWeight = false;
        }

        // 权重值相等的话，直接按数组的数量来随机，如果权重值不相等的话，带上权重值来计算
        int[] weights = new int[invokerNum];
        int offset = 0;
        if (sameWeight) {
            offset = ThreadLocalRandom.current().nextInt(invokerNum);
        } else {
            // 计算总的权重
            int totalWeight = 0;
            for (int i = 0; i < invokerNum; i++) {
                // 将指定的权重放在第一个元素（权重越大）
                if (i == 0) {
                    weights[i] = weight;
                    totalWeight += weight;
                    continue;
                }
                weights[i] = DEFAULT_WEIGHT;
                totalWeight += weights[i];
            }

            // 按总的权重作为随机数种子进行随机处理
            int randomValue = ThreadLocalRandom.current().nextInt(totalWeight);

            // 计算出对应的元素（将获取的权重值，从第一个元素开始递减权重值，直到为负数，并对应获取下标）
            for (int i = 0; i < invokerNum; i++) {
                randomValue -= weights[i];
                if (randomValue < 0) {
                    offset = i;
                    break;
                }
            }

        }

        // 返回调用元素对应的序号（数组下标 + 1）
        return offset + 1;
    }
}
