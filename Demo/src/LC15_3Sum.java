import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC15_3Sum {
    /*    获取数组中三个数为0的所有三元组列表*/
    public List<List<Integer>> threeSum(int[] nums) {
        List <List<Integer>> listSum=new ArrayList<List<Integer>>() ;
        if(nums==null || nums.length<3)
            return listSum;

        quickSort(nums,0,nums.length-1); //排序
       /* Arrays.sort(nums);  //排序*/
        int n=nums.length;
        for (int i = 0; i <n-2 ; i++) {
            int a=nums[i];
            if(i>0 && nums[i-1]==nums[i])
                continue;
            if(a>0)
                break;;

            int target=-a;
            int low=i+1,high=n-1;
            while(low<high){
                int sum=nums[low]+nums[high];
                if(sum==target){
                    listSum.add(Arrays.asList(a,nums[low],nums[high]));
                    while(low+1<n && nums[low+1]==nums[low])
                        low++;
                    while(high-1>=0 && nums[high-1]==nums[high])
                        high--;
                    low++;high--;
                }else if(sum>target){
                    high--;
                }else{
                    low++;
                }
            }
        }
        return listSum;
    }

    /*    获取数组中三个数为0的所有三元组列表*/
    public List<List<Integer>> threeSum1(int[] nums) {
        List <List<Integer>> listSum=new ArrayList<List<Integer>>() ;
        if(nums==null || nums.length<3)
            return listSum;

        quickSort(nums,0,nums.length-1); //排序

        int low=0,high=nums.length-1;
        while((high-low)>=2){
            List<Integer> suml=getSum(nums,low,high);
            if(suml!=null)
                listSum.add(suml);

            if(high-1>=0 && nums[high]==nums[high-1])
                high--;
            suml=getSum(nums,low,high-1);
            if(suml!=null)
                listSum.add(suml);

            if(low+1<nums.length && nums[low]==nums[low+1])
                low++;
            suml=getSum(nums,low+1,high);
            if(suml!=null)
                listSum.add(suml);
            low++;high--;
        }
        return listSum;
    }


  /*   获取数组中三个数和为0的三元组*/
    public  List<Integer> getSum(int[] nums,int low,int high) {
        if((high-low)>=2){
            List<Integer> list=new ArrayList<>();
            int b=nums[low],c=nums[high];
            int a=0-(b+c);
            low++;
            high--;
            while(low<=high){
                int mid=(low+high)/2;
                if(nums[mid]==a){
                    list.add(b);
                    list.add(a);
                    list.add(c);
                    return list;
                }else if(nums[mid]<a){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
        }
        return null;
    }

    public  int partition(int[] a,int low,int high){
        int pivot=a[low];
        while(low<high){
            while(low<high && a[high]>=pivot)
                high--;
            a[low]=a[high];
            while(low<high && a[low]<=pivot)
                low++;
            a[high]=a[low];
        }
        a[low]=pivot;
        return low;
    }

    /*    快速排序*/
    public  void quickSort(int[] a,int low,int high){
        if(low<high){
            int pivotindex=partition(a,low,high);
            quickSort(a,low,pivotindex-1);
            quickSort(a,pivotindex+1,high);
        }
    }


    public static void main(String[] args) {
        LC15_3Sum lc15_3Sum=new LC15_3Sum();
        int[] nums={-1, 0, 1, 2, -1, -4};
        System.out.println(lc15_3Sum.threeSum(nums));
    }
}
