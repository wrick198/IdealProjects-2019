import java.util.*;

public class sxf2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()) {
            ArrayList<Integer> arr=new ArrayList<>();
            int n=sc.nextInt();
            for(int i=0;i<n;i++){
                arr.add(sc.nextInt());
            }

            int target=100;
            int i=0;
            while(target!=0){
                target=100;
                target-=arr.get(i);

                i++;
            }

        }
    }
}
