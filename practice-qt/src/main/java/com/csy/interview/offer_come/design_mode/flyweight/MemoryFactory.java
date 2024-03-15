package com.csy.interview.offer_come.design_mode.flyweight;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class MemoryFactory {

    private static List<Memory> memoryList = new ArrayList<>();

    public static Memory getMemory(int size) {
        Memory memory = null;
        for (int i = 0; i < memoryList.size(); i++) {
            memory = memoryList.get(i);
            if (memory.getSize() == size && memory.isUsed() == false) {
                memory.setUsed(true);
                memoryList.set(i, memory);
                System.out.println("get memory form memoryList:" + JSON.toJSONString(memory));
                break;
            }
        }

        if (memory == null) {
            memory = new Memory(32, false, UUID.randomUUID().toString());
            System.out.println("create a new memory from system and add to memoryList:" + JSON.toJSONString(memory));
            memoryList.add(memory);
        }
        return memory;
    }

    public static void releaseMemory(String id) {
        for (int i = 0; i < memoryList.size(); i++) {
            Memory memory = memoryList.get(i);
            if (memory.getId().equals(id)) {
                memory.setUsed(false);
                memoryList.set(i, memory);
                System.out.println("release memory:" + id);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Memory memory = MemoryFactory.getMemory(32);
        MemoryFactory.releaseMemory(memory.getId());
        MemoryFactory.getMemory(32);

        /**
         * 输出结果：
         * create a new memory from system and add to memoryList:{"id":"7874e99a-3b39-4eb7-bd4e-46a99084d5a8","size":32,"used":false}
         * release memory:7874e99a-3b39-4eb7-bd4e-46a99084d5a8
         * get memory form memoryList:{"id":"7874e99a-3b39-4eb7-bd4e-46a99084d5a8","size":32,"used":true}
         *
         * 结果分析：
         *
         * 问题点答疑：
         */
    }
}
