public class ClassTest {
    public static void main(String[] args) {
        Child child = new Child(1);
        child.show();
    }
}

class Parent {
    public String str;
    Parent(){
        this(1);
    }
    Parent(int a) {
        this.str = "Parent";
        this.show();
    }
    public void show() {
        System.out.println(this.str);
    }
}

class Child extends Parent {
    public String str;
    Child() {
    }
    Child(int a) {
        str = "Child";
    }
    public void show() {
        System.out.println(str);
        super.show();
    }
}
