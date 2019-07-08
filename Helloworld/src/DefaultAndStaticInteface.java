import java.util.function.Supplier;

interface Defaultable{
    default String notRequired(){
        return "这是默认方法";
    }
}

class DefaultableImpl implements Defaultable{

}

class OverrideableImpl implements Defaultable{
    @Override
    public String notRequired() {
        return "继承重写方法";
    }
}

interface DefaultableFactory{
    static Defaultable create(Supplier<Defaultable> supplier){
        return supplier.get();
    }
}

public class DefaultAndStaticInteface {
    public static void main(String[] args) {
        Defaultable defaultable=DefaultableFactory.create(DefaultableImpl::new);
        System.out.println(defaultable.notRequired());
        defaultable=DefaultableFactory.create(OverrideableImpl::new);
        System.out.println(defaultable.notRequired());
    }
}

/*
//创建静态内部类
OuterClass.NestedStaticClass printer=new OuterClass.NestedStaticClass();
//创建非静态内部类
OuterClass.InnerClass innerObject=new OuterClass().new Innerclass();
 */