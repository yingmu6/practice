package com.csy.structure.array.ext;

/**
 * @author chensy
 * @date 2023/9/5
 */
public class MyArrayList {

    private transient Object[] data = null;

    private int size = 0; //元素的实际数量

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("illegal capacity value");
        } else {
            this.data = new Object[capacity]; //构建指定数量的数组
        }
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public void checkIncrease(int index, Object obj) {
        if (size >= data.length) { //判断是否需要扩容
            Object[] newData = new Object[size * 2]; //按两倍扩容
            if (index == -1 && obj == null) { //数组拷贝
                System.arraycopy(data, 0, newData, 0, size);
            } else {
                System.arraycopy(data, index, newData, index + 1, size - index);
            }

            data = newData;
            newData = null;
        }
    }

    public int getSize() {
        return this.size;
    }

    public int indexOf(Object o) { //获取列表中对象对应的下标
        if (o == null) {
            for (int i = 0; i < this.size; i++) {
                if (data[i] == null) { //找出第一个为null的元素下标
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (o.equals(data[i])) { //使用equals比较对象是否相等
                    return i;
                }
            }
        }
        return -1; //若没找到元素，则返回-1
    }

    public boolean add(Object obj) { //在尾部添加元素
        checkIncrease(-1, null);
        data[size++] = obj;
        return true;
    }

    public boolean checkIndexOut(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        return true;
    }

    public boolean add(int index, Object obj) { //在指定位置添加元素
        if (index == size) { //尾部位置添加
            add(obj);
        } else if (checkIndexOut(index)) {
            if (size < data.length) {
                System.arraycopy(data, index, data, index + 1, size - index); //将index位置后面的元素，通过数组拷贝，整体向后移动
                data[index] = obj; //设置index位置的值
            } else { //容量不够，需要先扩容
                checkIncrease(index, obj);
            }
            size ++;
        }
        return true;
    }

    public Object get(int index) {
        checkIndexOut(index);
        return data[index];
    }

    public void removeAll() {
        for (int i = 0; i < this.size; i++) {
            data[i] = null;
        }
    }

    public Object remove(int index) {
        if (index == size + 1) {
            throw new IndexOutOfBoundsException("索引越界");
        } else if (checkIndexOut(index)) {
            Object obj = data[index];
            if (index == size) {
                data[index] = null;
            } else {
                System.arraycopy(data, index + 1, data, index, size - index);
            }

            size--;
            return obj;
        }
        return null;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contain(Object obj) {
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(data[i])) {
                return true;
            }
        }
        return false;
    }
}
