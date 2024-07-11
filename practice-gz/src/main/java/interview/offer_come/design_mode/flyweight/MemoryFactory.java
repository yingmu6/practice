package interview.offer_come.design_mode.flyweight;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class MemoryFactory { //@MsY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     *
     * 问题点答疑：
     * 1）该设计模式的用途以及优势是什么？
     */

    private static List<Memory> memoryList = new ArrayList<>();

    public static Memory getMemory(int size) { //获取指定大小的内存
        Memory memory = null;
        for (int i = 0; i < memoryList.size(); i++) {
            memory = memoryList.get(i);
            if (memory.getSize() == size && memory.isUsed() == false) {
                memory.setUsed(true); //更改为"已使用"
                memoryList.set(i, memory);
                System.out.println("get memory form memoryList:" + JSON.toJSONString(memory));
                break;
            }
        }

        if (memory == null || memory.getSize() != size) { //未找到指定大小的内存，则进行创建
            memory = new Memory(size, false, UUID.randomUUID().toString());
            System.out.println("create a new memory from system and add to memoryList:" + JSON.toJSONString(memory));
            memoryList.add(memory);
        }
        return memory;
    }

    public static void releaseMemory(String id) {
        for (int i = 0; i < memoryList.size(); i++) {
            Memory memory = memoryList.get(i);
            if (memory.getId().equals(id)) {
                memory.setUsed(false); //更改为"未使用"
                memoryList.set(i, memory);
                System.out.println("release memory:" + id);
                break;
            }
        }
    }

    public static void main(String[] args) { //Done
        Memory memory = MemoryFactory.getMemory(64);
        MemoryFactory.releaseMemory(memory.getId());
        MemoryFactory.getMemory(32);

        /**
         * 输出结果：
         * create a new memory from system and add to memoryList:{"id":"50255905-7504-472a-b5db-3986fbc1ecfd","size":64,"used":false}
         * release memory:50255905-7504-472a-b5db-3986fbc1ecfd
         * create a new memory from system and add to memoryList:{"id":"d32ebdbe-6282-4a4f-9b4f-051b12b3057d","size":32,"used":false}
         *
         * 结果分析：
         * 1）在getMemory(size)中，会根据size从MemoryFactory中的List查找对应size的Memory
         *
         * 2）在releaseMemory(id)中，会更改Memory的标志isUsed来判断是否能使用
         *
         */
    }
}
