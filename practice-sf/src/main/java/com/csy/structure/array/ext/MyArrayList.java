package com.csy.structure.array.ext;

/**
 * @author chensy
 * @date 2023/9/5
 */
public class MyArrayList {

    private transient Object[] data = null;

    private int size = 0;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("illegal capacity value");
        } else {
            this.data = new Object[capacity];
        }
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public void checkIncrease(int index, Object obj) {
        if (size >= data.length) {
            Object[] newData = new Object[size * 2];
            if (index == -1 && obj == null) {
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

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < this.size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean add(Object obj) {
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

    public boolean add(int index, Object obj) {
        if (index == size) {
            add(obj);
        } else if (checkIndexOut(index)) {
            if (size < data.length) {
                System.arraycopy(data, index, data, index + 1, size - index);
                data[index] = obj;
            } else {
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
