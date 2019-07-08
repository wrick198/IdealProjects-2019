import java.io.*;
import java.util.*;

public class JINRI1 {

    public static <T> Queue<T> deepCopy(Queue<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        Queue<T> dest = (Queue<T>) in.readObject();
        return dest;
    }

    public static int addOne(int n){
        if(n>=0 && n<10)
            return n;
        else if(n<0)
            return n+10;
        else
            return n-10;
    }

    public static Queue<Integer> getNextStep(int a,Queue<Integer> queTemp){
        queTemp.offer(addOne(a+1));
        queTemp.offer(addOne(a-1));
        return queTemp;
    }

    public static int getMethodFun(int n){
        if(n<1)
            return 0;

        int target=0,a=0;
        Queue<Integer> que=new LinkedList<>();
        Queue<Integer> queTemp=new LinkedList<>();
        que.offer(addOne(a+1));
        que.offer(addOne(a-1));


        for(int i=1;i<n;i++){
            while(!que.isEmpty()){
                queTemp=getNextStep(que.poll(),queTemp);
            }
            try{
                que=deepCopy(queTemp);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            queTemp.clear();
        }

        int methodSum=0;
        for(Integer des:que){
            System.out.println(des);
            if(des==target)
                methodSum+=1;
        }
        return methodSum;
    }

    public static void main(String []args) {
        Queue<Integer> que=new LinkedList<>();
        que.offer(1);
        que.offer(2);
        que.offer(3);

        que.poll();

        System.out.println(que.size());

        Queue<String> str=new LinkedList<String>();
        str.offer("abc");
        str.offer("123");
        System.out.println(str.size());

        for(String s:str)
            System.out.println(s);

        Queue<Integer> que1=new LinkedList<>();
        que1=que;
        for(Integer a:que1){
            System.out.println(a);
        }
        System.out.println(getMethodFun(4));
    }
}
