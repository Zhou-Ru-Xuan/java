package com.zhouruxuan.behavioral.observer.demo3;

import com.zhouruxuan.behavioral.observer.demo3.editor.Editor;
import com.zhouruxuan.behavioral.observer.demo3.listener.EmailNotificationListener;
import com.zhouruxuan.behavioral.observer.demo3.listener.LogOpenListener;

public class Demo {
    private static final String RELATIVE_PATH = "design-pattern/src/main/java/com/zhouruxuan/behavioral/observer/demo3/static";

    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener(RELATIVE_PATH + "/path/to/log/file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}