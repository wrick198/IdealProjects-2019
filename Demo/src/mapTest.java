import java.util.*;

public class mapTest {
    public static void main(String[] args) {
        treemapDemo();
        ArraylistDemo();
    }

    public static void treemapDemo(){
        Map<String,String> map=new TreeMap<>();
        map.put("11", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("d", "ddddd");
        Set<String> keyset=map.keySet();
        Iterator<String> iter=keyset.iterator();
        while (iter.hasNext()){
            String key=iter.next();
            System.out.println(key+","+map.get(key));
        }
    }

    public static void ArraylistDemo(){
        List<Integer> arr=new ArrayList<>();
        arr.add(1);
        System.out.println(arr.contains(1));
    }
}
