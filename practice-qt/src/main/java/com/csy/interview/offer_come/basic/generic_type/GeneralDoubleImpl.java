package com.csy.interview.offer_come.basic.generic_type;

/**
 * @author chensy
 * @date 2024/3/18
 */
public class GeneralDoubleImpl implements IGeneral<Double> {

    private Double id;

    @Override
    public void setId(Double id) {
        this.id = id;
    }

    @Override
    public Double getId() {
        return id;
    }
}
