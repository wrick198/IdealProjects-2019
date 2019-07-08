class Art {
    Art(){
        System.out.println("base");
    }
    void print(){
        System.out.println("print art");
    }
}

class Drawing extends Art{
    Drawing(){
        System.out.println("draw");
    }
    @Override
    void print(){
        System.out.println("print drawing");
    }

    void draw(){
        System.out.println("begin to draw");
    }
}

public class Cartoon extends Drawing{
    Cartoon(){
        System.out.println("cartoon");
    }

    void print(){
        System.out.println("print cartoon");
    }

    void drawCartoon(){
        System.out.println("print drawcartoon");
    }

    public static void main(String[] args) {
        Drawing cartoon=new Cartoon();
        cartoon.print();
        cartoon.draw();
        ((Cartoon)cartoon).drawCartoon();
    }

}