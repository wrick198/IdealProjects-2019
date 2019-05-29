import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
interface Supplier<T>{
    T get();

}

class Car{
    public static Car create(final Supplier<Car> sup){
        return sup.get();
    }
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}

public class MethodYinyong {
    public static void main(String[] args) {
        List names=new ArrayList();

        names.add("google");
        names.add("Baidu");
        names.forEach(System.out::println);
        names.forEach(n-> System.out.println(n));

        Car car=Car.create(Car::new);
        car.repair();

        Runnable r=()-> System.out.println("线程开始启动");
        new Thread(r).start();

        Consumer<Integer> consumer=(x)-> System.out.println(x); //一个参数，无返回值
        consumer.accept(5);  //Lambda表达式实现了accept函数的功能。
        Consumer<Integer> consumer1=System.out::println;
        consumer1.accept(51);

        Function<String,Integer> f=s->{   //一个参数，一个返回值
            int result=Integer.parseInt(s);
            return result;
        };
        Function<String,Integer> f1=(x)->Integer.parseInt(x); //只有一条语句的时候，可以去掉大括号和return
        System.out.println(f.apply("123"));
        Function<Integer,int[]> fun=int[]::new;
        int[] arr=fun.apply(10);

        BiFunction<Integer,Integer,String> b=(x,y)->x+y+"";
        System.out.println(b.apply(1,2));

        BiPredicate<String,String> biPredicate=(x,y)->x.compareTo(y)>=0?true:false;
        System.out.println(biPredicate.test("123","122"));


    }
}
