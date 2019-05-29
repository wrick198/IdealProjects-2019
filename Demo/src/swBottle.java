import java.util.*;

public class swBottle {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Integer> arr=new ArrayList<>();
        while (sc.hasNextInt()){
            int numEmptyBo=sc.nextInt();
            if(numEmptyBo==0)
                break;
            int bottle=0;
            while(numEmptyBo>2){
                int a=numEmptyBo%3;
                int b=(numEmptyBo-a)/3;
                bottle+=b;
                numEmptyBo=a+b;
            }
            if(numEmptyBo==2)
                bottle+=1;
            arr.add(bottle);
        }
        for(int bo:arr)
            System.out.println(bo);

    }

}
