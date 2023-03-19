package com.zhouruxuan.creational.abstractfactory.demo2.factories;

import com.zhouruxuan.creational.abstractfactory.demo2.buttons.Button;
import com.zhouruxuan.creational.abstractfactory.demo2.buttons.MacOSButton;
import com.zhouruxuan.creational.abstractfactory.demo2.checkboxes.Checkbox;
import com.zhouruxuan.creational.abstractfactory.demo2.checkboxes.MacOSCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}