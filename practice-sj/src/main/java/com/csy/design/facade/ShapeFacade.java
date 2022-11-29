package com.csy.design.facade;

/**
 * 形状门面（通过门面来与内部系统交互）
 * @author : chensy
 * Date : 2020/10/19 下午10:39
 */
public class ShapeFacade {
    private Rectangle rectangle;
    private Round round;

    public ShapeFacade(){
        rectangle = new Rectangle();
        round = new Round();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawRound() {
        round.draw();
    }
}
