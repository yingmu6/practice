package com.csy.spring.basic;


import org.springframework.stereotype.Service;

/**
 * @author chensy
 * @date 2021/9/8
 */
@Service
public class Animal {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
