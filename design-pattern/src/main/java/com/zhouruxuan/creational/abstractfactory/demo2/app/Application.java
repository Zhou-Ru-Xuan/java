package com.zhouruxuan.creational.abstractfactory.demo2.app;

import com.zhouruxuan.creational.abstractfactory.demo2.buttons.Button;
import com.zhouruxuan.creational.abstractfactory.demo2.checkboxes.Checkbox;
import com.zhouruxuan.creational.abstractfactory.demo2.factories.GUIFactory;

/**
 * Factory users don't care which concrete factory they use since they work with
 * factories and products through abstract interfaces.
 */
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}