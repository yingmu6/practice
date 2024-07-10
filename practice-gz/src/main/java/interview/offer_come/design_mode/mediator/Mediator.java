package interview.offer_come.design_mode.mediator;

/**
 * @author chensy
 * @date 2024/3/16
 */
public abstract class Mediator { //中介

    protected Colleague colleagueTenant;

    protected Colleague colleagueLandlord;

    public Mediator(Colleague colleagueTenant, Colleague colleagueLandlord) {
        this.colleagueTenant = colleagueTenant;
        this.colleagueLandlord = colleagueLandlord;
    }

    public abstract boolean notifyColleagueTenant(String message);

    public abstract boolean notifyColleagueLandlord(String message);
}
