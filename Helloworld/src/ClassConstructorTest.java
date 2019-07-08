public class ClassConstructorTest {
    public static void main(String[] args) {
        Child1 child = new Child1();
        child.show();
    }
}

class Parent1 {
    private Parent1 mSelf;
    Parent1(){
        mSelf = this;
    }
    public void show() {
        System.out.println(this.getClass().getName());
        System.out.println(super.getClass().getName());
        System.out.println(mSelf.getClass().getName());
        sin()
    }
}

class Child1 extends Parent1 {
    @Override
    public void show() {
        System.out.println(this.getClass().getName());
        System.out.println(super.getClass().getSuperclass().getName());
        super.show();
    }
}
