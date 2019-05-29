
import java.util.*;
import java.util.Map.Entry;


public class MapSort {
    static void print(){
        System.out.println("123");
    }
    public static void main(String[] args) {
        print();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("a", "ddddd");
        map.put("c", "bbbbb");
        map.put("e", "ccccc");
        map.put("d", "aaaaa");
        map.put("b", "ccccc");


        //这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        //然后通过比较器来实现排序
        if(map.containsKey("a"))
            map.put("a","ddddd1");
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            public int compare(Entry<String, String> o1,
                               Entry<String, String> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });

        for(Map.Entry<String,String> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }

    }
}


