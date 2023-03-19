package com.zhouruxuan.creational.abstractfactory.demo2.factories;

import com.zhouruxuan.creational.abstractfactory.demo2.buttons.Button;
import com.zhouruxuan.creational.abstractfactory.demo2.buttons.WindowsButton;
import com.zhouruxuan.creational.abstractfactory.demo2.checkboxes.Checkbox;
import com.zhouruxuan.creational.abstractfactory.demo2.checkboxes.WindowsCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}