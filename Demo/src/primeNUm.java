import java.util.Scanner;
import java.lang.Math;

public class primeNUm {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNextInt()){
            int n=sc.nextInt();
            if(n==2) {
                System.out.println(0);
                continue;
            }
            if(n==3) {
                System.out.println(1);
                continue;
            }
            int sum=0;
            for (int i = 3; i <n ; i+=2) {
                sum+=isPrime(i);
            }
            System.out.println(sum+1);
        }
    }

    public static int isPrime(int n){
        int n2=(int)Math.sqrt(n);
        int i=2;
        while(i<=n2){
            if(n%i==0)
                return 0;
            i++;
        }
        return 1;
    }
}
