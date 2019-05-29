package com.objectPattern;

interface Shape{
    public void draw();
}

class Circle implements Shape{

    public Circle(){

    }
    @Override
    public void draw(){
        System.out.println("draw cicle");
    }
}

class Rectangle implements Shape{
    public Rectangle(){

    }
    @Override
    public void draw(){
        System.out.println("draw Rectangle");
    }
}

class Square implements Shape{
    public Square(){
        super();
    }
    @Override
    public void draw(){
        System.out.println("draw Square");
    }
}

public class FacadePattern {
    private Shape cir;
    private Shape rec;
    private Shape squ;

    public FacadePattern(){
        cir=new Circle();
        rec=new Rectangle();
        squ=new Square();
    }

    public void drawCircle(){
        cir.draw();
    }

    public void drawRectangle(){
        rec.draw();
    }

    public void drawSquare(){
        squ.draw();
    }

    public static void main(String[] args) {
        FacadePattern fac=new FacadePattern();
        fac.drawCircle();
        fac.drawRectangle();
        fac.drawSquare();
    }

}
