
public class RadixSort {
    public static void main(String[] args) {
        int[] arr=new int[25];
        for(int i=1;i<25;i++){
            arr[i]=i;
        }
        RSort(arr);
        for(int a:arr)
            System.out.println(a);
    }

    public static void RSort(int [] arr){
        int maxValue=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]>maxValue)
                maxValue=arr[i];
        }
        for(int reg=1;maxValue/reg>0;reg*=10){
            sortByreg(arr,reg);
        }
    }

    public static void sortByreg(int[] arr,int reg){
        int[] outarr=new int[arr.length];
        int[] bukctNum=new int[10];
        for(int i=0;i<arr.length;i++){
            bukctNum[(arr[i]/reg)%10]++;
        }
        for(int i=1;i<10;i++){
            bukctNum[i]+=bukctNum[i-1];
        }
        for(int j=arr.length-1;j>=0;j--){
            int pos=bukctNum[(arr[j]/reg)%10]-1;
            outarr[pos]=arr[j];
            bukctNum[(arr[j]/reg)%10]--;
        }
        for(int i=0;i<arr.length;i++) {
            arr[i]=outarr[i];
        }
    }
}
