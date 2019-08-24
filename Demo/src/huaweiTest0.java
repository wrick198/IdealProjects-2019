import jdk.jfr.Unsigned;

import java.util.HashMap;
import java.util.Map;

public class huaweiTest0 {
    public void change(){

    }
    Map<String,String> mapnum=new HashMap<>(){{
        put("a","b");
    }};

    public static void main(String[] args) {
        String a="1234";

        String b="11";
        int c=Integer.parseInt(a)-Integer.parseInt(b);
        System.out.println(c);
        System.out.println(a.equals(b));

        System.out.println(a.substring(0,1));
        System.out.println(a);
        System.out.println(a.substring(1));
        System.out.println(a);

        int cc=Integer.MIN_VALUE;
        long d=(long)cc;
        d=Math.abs(d);
        System.out.println(d);
        System.out.println(Math.abs(Integer.MIN_VALUE));

        int dd=-1;
        int dr=-1;
        System.out.println((dd^dr)<0);
    }

    Map<String,String> phone=new HashMap<>(){{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");

    }};
}

