package com.zhouruxuan.behavioral.momento.demo2.history;

import com.zhouruxuan.behavioral.momento.demo2.editor.Editor;

public class Memento {
    private String backup;
    private Editor editor;

    public Memento(Editor editor) {
        this.editor = editor;
        this.backup = editor.backup();
    }

    public void restore() {
        editor.restore(backup);
    }
}