

import java.io.IOException;
import java.util.*;

public class Main {

    public  void method(){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<String,Integer> hostId=new LinkedHashMap<>(n);
        Map<Integer,String> logText=new LinkedHashMap<>(n);
        ArrayList<Integer> result=new ArrayList<>();

        for (int i = 0; i <n ; i++) {
        }

        for (Integer re:result)
            System.out.println(re);
    }

    public void test(){
        try{
            int a=100/0;
        }catch (Exception e){
            System.out.println("1");
            throw new RuntimeException();
        }finally {
            System.out.println("2");
        }
        System.out.println(3);
    }


    public static void main(String[] args) {
        Main ma=new Main();
        int a=3,b=4;
        a*=(a+=b);

        int c=3,d=4;
        c=c*(c+=b);
        System.out.println(a);
        System.out.println(c);


    }

}
