package com.zhouruxuan.behavioral.command.demo2.commands;


import com.zhouruxuan.behavioral.command.demo2.editor.Editor;

public class CopyCommand extends Command {

    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }
}