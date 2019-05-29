package com.objectPattern;

import java.util.Collections;

interface AbstractFactory {
    Shape getShape();

    Color getColor();
}

class ShapeFactory implements AbstractFactory {
    private Shape shape;

    ShapeFactory(String className) {
        try {
            shape = (Shape) Class.forName(className).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public Color getColor() {
        return null;
    }
}

class ColorFactory implements AbstractFactory {
    private Color col;

    ColorFactory(String colorName) {
        try {
            col = (Color) Class.forName(colorName).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public Color getColor() {
        return col;
    }

    public void setColor(Color c) {
        this.col = c;
    }
}

interface Color {
    void fill();
}

class Red implements Color {
    public Red() {
    }

    @Override
    public void fill() {
        System.out.println("红色填充方法");
    }
}

class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("蓝色填充方法");
    }
}

class Green implements Color {
    @Override
    public void fill() {
        System.out.println("绿色填充方法");
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        try {
            AbstractFactory abstractFactory = new ShapeFactory("com.objectPattern.Rectangle");
            Shape shape = abstractFactory.getShape();
            shape.draw();

            AbstractFactory abstractFactory1 = new ColorFactory("com.objectPattern.Red");
            Color col = abstractFactory1.getColor();
            col.fill();
            ((ColorFactory) abstractFactory1).setColor(new Blue());
            col=abstractFactory1.getColor();
            col.fill();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
