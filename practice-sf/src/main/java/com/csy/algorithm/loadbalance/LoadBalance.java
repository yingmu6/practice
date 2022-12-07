package com.csy.algorithm.loadbalance;

/**
 * 负载均衡算法
 *
 * @Author chenSy
 * @Date 2022/12/07 13:46
 * @Description
 */
public interface LoadBalance {

    /**
     * 从指定的列表中选择其中一个元素
     * @param invokerNum 调用列表的数组
     * @param weight 当前调用的权重（权重越大，提前执行的概率越大）
     * @return 具体元素的所在序号
     */
    int select(int invokerNum, int weight);
}
