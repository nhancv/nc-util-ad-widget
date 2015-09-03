package com.active.sunnypoint.dagger;

/**
 * Created by NhanCao on 03-Sep-15.
 */
public class ControlBus {
    public Object className;
    public int dataCommand;

    public ControlBus(Object className, int dataCommand) {
        this.className = className;
        this.dataCommand = dataCommand;
    }
}
