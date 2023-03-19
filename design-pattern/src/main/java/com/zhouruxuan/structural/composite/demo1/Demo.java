package com.zhouruxuan.structural.composite.demo1;

import com.zhouruxuan.structural.composite.demo1.editor.ImageEditor;
import com.zhouruxuan.structural.composite.demo1.shapes.Circle;
import com.zhouruxuan.structural.composite.demo1.shapes.CompoundShape;
import com.zhouruxuan.structural.composite.demo1.shapes.Dot;

import com.zhouruxuan.structural.composite.demo1.shapes.Rectangle;

import java.awt.*;

/**
 * Simple and compound graphical shapes
 * This example shows how to create complex graphical shapes, composed of simpler shapes and treat both of them uniformly.
 */
public class Demo {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();

        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),

                new CompoundShape(
                    new Circle(110, 110, 50, Color.RED),
                    new Dot(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }
}