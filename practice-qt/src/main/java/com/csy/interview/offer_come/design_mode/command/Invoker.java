package com.csy.interview.offer_come.design_mode.command;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action(String commandMessage) {
        System.out.println("command sending...");
        command.exe(commandMessage);
    }
}
