package com.csy.interview.written_exam.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/28
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}