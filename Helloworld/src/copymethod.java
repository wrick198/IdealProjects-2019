import java.util.*;
import java.io.*;

public class copymethod {

    /*
    深拷贝
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    public static void main(String[] args) {
        List<Integer> arr1=new ArrayList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(4091);
        int a=arr1.get(0);
        System.out.println(a);

        List<Integer> arr2=new ArrayList<>();
        try{
            arr2=deepCopy(arr1); //深拷贝
        }
        catch (Exception e) {
            System.out.println(e);
        }

        List<Integer> arr3=new ArrayList<>(arr1); //通过构造函数复制

        List<Integer> arr4=new ArrayList<>(arr1.size()); //直接赋值
        for(Integer value:arr1){
            arr4.add(value);
        }


        Iterator<Integer>  it=arr2.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("修改前:");
        System.out.println(arr3.get(2));
        System.out.println(arr4.get(2));
        arr1.set(2,3);
        System.out.println("修改后:");
        System.out.println(arr3.get(2));
        System.out.println(arr4.get(2));

        Integer in1= Integer.valueOf(1);
        in1=2;
        System.out.println(in1);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
