package com.zhouruxuan.creational.factory.demo2.factory;

import com.zhouruxuan.creational.factory.demo2.buttons.Button;
import com.zhouruxuan.creational.factory.demo2.buttons.WindowsButton;

/**
 * Windows Dialog will produce Windows buttons.
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}