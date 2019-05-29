import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class divideStone {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str=sc.nextLine();
            String []strarr=str.split(",");
            List<Integer> arrlist=new ArrayList<>();
            int sum=0;
            for(String strnum:strarr){
                int num=Integer.parseInt(strnum);
                arrlist.add(num);
                sum+=num;
            }
            int b=sum/2;
            Collections.sort(arrlist);
            int sum1=0,i=0;
            while(sum1<=b&&i<arrlist.size()){
                sum1+=arrlist.get(i);
                i++;
            }
            int a=sum1-arrlist.get(i-1);
            System.out.println((sum-a)+","+a);

        }
    }
}
