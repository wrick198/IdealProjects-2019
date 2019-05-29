import javax.net.ssl.SSLEngine;
import javax.swing.text.ParagraphView;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class test
{
    public static int MoreThanHalfNum_Solution(int [] array) {
        if(array==null || array.length<1)
            return 0;
        Map<Integer,Integer> map=new HashMap<>();
        int len=array.length;
        for (int i = 0; i <len ; i++) {
            if(map.containsKey(array[i]))
                map.put(array[i],map.get(array[i])+1);
            else
                map.put(array[i],1);
            if(map.get(array[i])>len/2) {
                return array[i];
            }
        }
        return 0;
    }

    public static int MoreThanHalfNum_Solution1(int [] array) {
        if(array==null || array.length<1)
            return 0;
        int len=array.length;
        int count=1;
        int result=array[0];
        for (int i = 1; i < len; i++) {
            if(count==0){
                result=array[i];
                count=1;
            }else if(result==array[i]){
                count++;
            }
            else{
                count--;
            }
        }
        if(count>=0){
            int co=0;
            for (int i = 0; i < len; i++) {
                if(array[i]==result)
                    co++;
            }
            if(co<=(len>>1))
                result=0;
        }
        return result;
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> arrayList=new ArrayList<>();
        if(input==null || k<1 || k>input.length)
            return arrayList;
        if(input.length==k){
            for (int i = 0; i < input.length; i++) {
                arrayList.add(input[i]);
            }
            Collections.sort(arrayList);
            return arrayList;
        }

        int end=input.length-1;
        int index=Partition(input,0,end);
        k=k-1;
        while(index!=k){
            if(index<k){
                index=Partition(input,index+1,end);
            }else if(index>k){
                index=Partition(input,0,index-1);
            }
        }
        for (int i = 0; i <=k ; i++) {
            arrayList.add(input[i]);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static int Partition(int[] a,int begin,int end){
        int pivot=a[begin];
        while(begin<end){
            while(a[end]>=pivot && begin<end)
                end--;
            a[begin]=a[end];
            while(a[begin]<=pivot && begin<end)
                begin++;
            a[end]=a[begin];
        }
        a[begin]=pivot;
        return begin;
    }

    public static void mapFunction(){
        Map<String,Integer> map=new LinkedHashMap<>();
        map.put("aa",123);
        map.put("aa1",133);
        map.put("bb",456);
        map.put("cc",200);
        map.put("cc",map.get("cc")+1);
        System.out.println("包含:"+map.containsKey("aa"));

        boolean b=map.containsKey("aa1");
        System.out.println(b);
//        System.out.println(map.get("aa1"));

        List<Map.Entry<String,Integer>> mplist=new ArrayList<>(map.entrySet());
        Collections.sort(mplist, new Comparator<Map.Entry<String,Integer> >(){
            public int compare(Map.Entry<String,Integer> en1,Map.Entry<String,Integer> en2){
                Integer a=en1.getValue()/100;
                Integer b=en2.getValue()/100;
                return b.compareTo(a);
            }
        }
        );

        for(Map.Entry<String,Integer> entry:mplist){
            System.out.println(entry.getKey()+entry.getValue());
        }

        Integer a=201;
        System.out.println(a.compareTo(200));
    }

    public static void stringFunction(){
        String str="ab\\c";
        int pos=str.lastIndexOf('\\');
        str=str.substring(pos+1);
        System.out.println(pos);
        System.out.println(str);

        String str1="ab10";
        str1=str1.replace("10","c");
        System.out.println(str1);
    }

    public static void arrayListDemo(){
        ArrayList<Integer> arr1=new ArrayList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(1,3);

        ArrayList<Integer> arr2=new ArrayList<>();
        arr2.addAll(arr1);

        int[] arr3 = {1, 2, 3, 4, 5};
        int[] arr4 = Arrays.copyOf(arr3, 10);
        int[] arr5=arr4;
        for(int a:arr3)
            System.out.println(a);
    }

    public String PrintMinNumber(int [] numbers) {
        if(numbers==null | numbers.length<1)
           return null;
        int len=numbers.length;
        for (int i = 0; i < len; i++) {
            for (int j = len-1; j >i ; j--) {
                if(isMore(numbers[j-1],numbers[j])){
                    int temp=numbers[j-1];
                    numbers[j-1]=numbers[j];
                    numbers[j]=temp;
                }
            }
        }
        String str="";
        for (int i = 0; i < len; i++) {
            str+=Integer.toString(numbers[i]);
        }
        return str;
    }

    private boolean isMore(int a,int b){
        String stra=Integer.toString(a)+Integer.toString(b);
        String strb=Integer.toString(b)+Integer.toString(a);
        return stra.compareTo(strb)>0?true:false;
    }

    public int GetUglyNumber_Solution(int index) {
        if(index<1)
            return 0;

        int[] ugly=new int[index];
        ugly[0]=1;
        int uglyIndex=1;
        int mutilIn2=0,mutilIn3=0,mutilIn5=0;
        while(uglyIndex<index){
            int minValue=Math.min(ugly[mutilIn2]*2,Math.min(ugly[mutilIn3]*3,ugly[mutilIn5]*5));
            ugly[uglyIndex]=minValue;
            while(ugly[mutilIn2]*2<=ugly[uglyIndex])
                mutilIn2++;
            while(ugly[mutilIn3]*3<=ugly[uglyIndex])
                mutilIn3++;
            while(ugly[mutilIn5]*5<=ugly[uglyIndex])
                mutilIn5++;

            ++uglyIndex;
        }
        return ugly[uglyIndex-1];
    }

    public int FirstNotRepeatingChar(String str) {
        if(str=="")
            return -1;
        int len=str.length();
        if(len==1)
            return 0;

        Map<Character,Integer> map=new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            char c=str.charAt(i);
            if(map.containsKey(c))
                map.put(c,map.get(c)+1);
            else
                map.put(c,1);
        }
        boolean flag=false;
        char ch=' ';
        for(Map.Entry<Character,Integer> elem:map.entrySet()){
            if(elem.getValue()==1){
                flag=true;
                ch=elem.getKey();
                break;
            }
        }
        if(!flag)
            return -1;
        int j = 0;
        for (; j < len; j++) {
            if(str.charAt(j)==ch)
                break;
        }
        if(j==len)
            return -1;
        else
            return j;
    }

    public int InversePairs(int [] array) {
        if(array==null)
            return 0;
        int len=array.length;
        if(len==1)
            return 0;

        int count=0;
        for (int i = 0; i <len-1 ; i++) {
            for (int j = i+1; j <len ; j++) {
                if(array[i]>array[j])
                    count++;
            }
        }
        return count%1000000007;
    }

    void reverseStack(Stack<Integer> stack){
        if(stack.size()<=1)
            return;
        else{
            int temp=stack.pop();
            reverseStack(stack);
            pushBottom(stack,temp);
        }
    }

    void pushBottom(Stack<Integer> stack,int value){
        if(stack.isEmpty()) {
            stack.push(value);
            return;
        }
        else{
            int temp=stack.pop();
            pushBottom(stack,value);
            stack.push(temp);
        }
    }

    public static void main(String[] args)
    {
//        Scanner sc = new Scanner(System.in);
//
//        // Character input
//        char c = sc.next().charAt(0);
//
//        // Print the read value
//        System.out.println("c = "+c);
//        System.out.println(c=='q');
//
//        String str = sc.next();
//        System.out.println(str.equals("ab"));
//        System.out.println(str);

        System.out.println();
        mapFunction();
        stringFunction();
        arrayListDemo();
//        System.out.println("1"+2);
        int[] a={4,2,1,4,2,4};
//        MoreThanHalfNum_Solution1(a);
//        GetLeastNumbers_Solution(a,2);

        int[] b={3,5,1,4,2};
        test t=new test();
        t.PrintMinNumber(b);
        System.out.println(t.GetUglyNumber_Solution(1500));
        t.FirstNotRepeatingChar("a a");
        int[] c={7,5,6,4,1};
        t.InversePairs(c);
    }

}