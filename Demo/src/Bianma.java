import jdk.jfr.Unsigned;

public class Bianma {
    public static int NumberOf1(int n) {
        int count=0;
        while(n!=0){
            count++;
            n=n&(n-1);
        }
        return count;
    }
    public static void main(String[] args) {
        int a=(int)(Math.pow(2,31)-1);  //2147483647
        System.out.println(a);
        System.out.println(a<<1);

        int b=(int)(Math.pow(2,31)-1);
        System.out.println(b);
        System.out.println(b<<1);
        System.out.println(NumberOf1(-1));

    }
}
