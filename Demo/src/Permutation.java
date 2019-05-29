import java.util.ArrayList;
import java.util.Collections;

public class Permutation {

    ArrayList<String> strlist=new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
        if(str==null || str.length()<1)
            return strlist;
        StringBuilder strbu=new StringBuilder(str);
        Permutation(strbu,0,str.length());
        Collections.sort(strlist);
        System.out.println(strlist);
        return strlist;

    }

    public ArrayList<String> Permutation(StringBuilder str,int k,int len) {
        if(k==len){
            if(!strlist.contains(new String(str))) {
                System.out.println(str);
                strlist.add(new String(str));
            }
        }
        else {
            for (int i = k; i < len; i++) {
                char c = str.charAt(k);
                str.setCharAt(k, str.charAt(i));
                str.setCharAt(i, c);
                Permutation(str, k + 1, len);
                c = str.charAt(k);
                str.setCharAt(k, str.charAt(i));
                str.setCharAt(i, c);
            }
        }
        return strlist;
    }

    /*
    字符串有重复的字符
     */
    public ArrayList<String> PermutationNoEqual(StringBuilder str,String strTemp,int k,int len) {
        if(k==len){
            System.out.println(str);
            strlist.add(new String(str));
        }else{
            for (int i = 0; i < len; i++) {
                str.setCharAt(k,strTemp.charAt(i));
                int j;
                for (j = 0; j < k; j++) {
                    if(strTemp.charAt(i)==str.charAt(j))
                        break;
                }
                if(j==k){
                    PermutationNoEqual(str,strTemp,k+1,len);
                }
            }
        }
        return strlist;
    }

    public static void main(String[] args) {
        Permutation pe=new Permutation();
        pe.Permutation("aab");
    }
}
