package com.csy.interview.offer_come.design_mode.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ListCollection implements Collection {

    public List list = new ArrayList<>();

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(this);
    }

    @Override
    public Object get(int i) {
        return list.get(i);
    }

    @Override
    public boolean add(Object object) {
        list.add(object);
        return true;
    }

    @Override
    public int size() {
        return list.size();
    }
}
