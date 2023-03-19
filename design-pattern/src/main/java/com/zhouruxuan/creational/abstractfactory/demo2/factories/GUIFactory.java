package com.zhouruxuan.creational.abstractfactory.demo2.factories;

import com.zhouruxuan.creational.abstractfactory.demo2.buttons.Button;
import com.zhouruxuan.creational.abstractfactory.demo2.checkboxes.Checkbox;

/**
 * Abstract factory knows about all (abstract) product types.
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}