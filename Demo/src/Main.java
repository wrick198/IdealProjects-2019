
import javax.swing.plaf.metal.MetalIconFactory;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {
    static void GetResult() {
        String str1=new String();
        System.out.println(str1.equals(""));
        System.out.println(str1==null);

        int[] a={1,2,3};
        System.out.println(a.length);

        String reg1="\\.\\s*";
        String reg2=".*"; //匹配所有，为空

        String str="A. b. c.";
        String[] strreg=str.split(reg1);
        System.out.println(strreg);
    }



    public static void main(String[] args){
        GetResult();
    }
}