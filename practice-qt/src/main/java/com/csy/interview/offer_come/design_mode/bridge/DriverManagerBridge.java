package com.csy.interview.offer_come.design_mode.bridge;

/**
 * @author chensy
 * @date 2024/3/15
 */
public abstract class DriverManagerBridge {

    private Driver driver;

    public void execute() {
        this.driver.executeSQL();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
