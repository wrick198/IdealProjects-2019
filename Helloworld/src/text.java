import java.util.*;

public class text{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            ArrayList<Integer> arr=new ArrayList<>();
            int n=sc.nextInt();
            for(int i=0;i<n;i++){
                arr.add(sc.nextInt());
            }
            ArrayList<Integer> arr1=new ArrayList<>();
            int index=0,j=0;
            arr1.add(arr.get(j));
            j++;
            index++;
            boolean flag=true;
            while(index<10 && j<n){
                int a=arr.get(j);
                for(Integer number:arr1){
                    if(number==a){
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    arr1.add(a);
                    index++;
                }
                flag=true;
                j++;
            }
            System.out.println(arr1.size());
            for(Integer number:arr1){
                System.out.println(number);
            }
        }
    }
}