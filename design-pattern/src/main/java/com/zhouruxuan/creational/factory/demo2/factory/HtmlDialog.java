package com.zhouruxuan.creational.factory.demo2.factory;

import com.zhouruxuan.creational.factory.demo2.buttons.Button;
import com.zhouruxuan.creational.factory.demo2.buttons.HtmlButton;

/**
 * HTML Dialog will produce HTML buttons.
 */
public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}