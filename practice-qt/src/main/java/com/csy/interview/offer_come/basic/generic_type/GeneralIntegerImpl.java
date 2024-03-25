package com.csy.interview.offer_come.basic.generic_type;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class GeneralIntegerImpl implements IGeneral<Integer> {

    private Integer id;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
