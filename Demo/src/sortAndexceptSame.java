
import java.util.*;

public class sortAndexceptSame {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Integer> arr=new ArrayList<>();
        while(sc.hasNext()){
            int n=sc.nextInt();
            for (int i = 0; i < n; i++) {
                arr.add(sc.nextInt());
            }
//            Collections.sort(arr);
            quickSort(arr,0,arr.size()-1);
            System.out.println(arr.get(0));
            for(int i=1;i<arr.size();i++){
                if(arr.get(i)==arr.get(i-1))
                    continue;
                System.out.println(arr.get(i));
            }
        }
    }

    public static void quickSort(List<Integer> arr,int low,int high){
        if(low>=high)
            return;
        int pivotPos=partition(arr,low,high);
        quickSort(arr,low,pivotPos-1);
        quickSort(arr,pivotPos+1,high);
    }

    public static int partition(List<Integer> arr,int low,int high){
        int pivot=arr.get(low);
        while(low<high){
            while (arr.get(high)>=pivot && low<high)
                high--;
            arr.set(low,arr.get(high));
            while(arr.get(low)<=pivot && low<high)
                low++;
            arr.set(high,arr.get(low));
        }
        arr.set(low,pivot);
        return low;
    }

}
