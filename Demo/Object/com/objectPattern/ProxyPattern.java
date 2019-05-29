package com.objectPattern;

interface Image{
   void display();
}

class RealImage implements Image{
    String filename;
    RealImage(String filename){
        this.filename=filename;
        loadImage(this.filename);
    }
    @Override
    public void display() {
        System.out.println("显示:"+filename);
    }

    public void loadImage(String filename){
        System.out.println("加载照片..."+filename);
    }

}

class ProxyImage implements Image{
    private RealImage realImage;
    String filename;
    ProxyImage(String filename){
        this.filename=filename;
    }

    @Override
    public void display() {
        if(realImage==null)
            realImage=new RealImage(filename);
        realImage.display();
    }
}
public class ProxyPattern {
    public static void main(String[] args) {
        ProxyImage proxyImage=new ProxyImage("123.figure");
        proxyImage.display();
        proxyImage.display();
    }
}
