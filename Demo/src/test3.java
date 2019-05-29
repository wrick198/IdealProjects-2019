
public class test3 {
    int a;
    String str;

    public test3(){
        this(20);
    }

    public test3(int a){
        this.a=a;
    }

    public static void main(String[] args) {
        test3 t=new test3();
        System.out.println(t.a);
    }

}
