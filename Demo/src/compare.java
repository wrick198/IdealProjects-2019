import java.util.*;

public class compare {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
//        while(sc.hasNext()) {
//            String word=sc.next();
//            str+=word+" ";
//        }
        int pos=str.lastIndexOf("-");
        String strA=str.substring(0,pos);
        String strB=str.substring(pos+1,str.length());
        String [] strAarr=strA.split(" ");
        String [] strBarr=strB.split(" ");
        int lenA=strAarr.length;
        int lenB=strBarr.length;
        if(strA.lastIndexOf("joker")!=-1 && strA.lastIndexOf("JOKER")!=-1) {
            System.out.println(strA);
            return;
        }
        else if(strB.lastIndexOf("joker")!=-1 && strB.lastIndexOf("JOKER")!=-1){
            System.out.println(strB);
            return;
        }
        boolean equalA=isequalA(strA);
        boolean equalB=isequalA(strB);
        if(lenA==lenB){
            if(equalA && equalB){
                if(compareMax(strA,strB)){
                    System.out.println(strA);
                    return;
                }
                else{
                    System.out.println(strB);
                    return;
                }
            }
            else if(equalA && lenA==4){
                System.out.println(strA);
                return;
            }
            else if(equalB && lenB==4){
                System.out.println(strB);
                return;
            }
            else if(lenA==5){
                if(compareMax(strA,strB)){
                    System.out.println(strA);
                    return;
                }
                else{
                    System.out.println(strB);
                    return;
                }
            }
            else{
                System.out.println("ERROR");
                return;
            }
        }
        else {
            if (equalA && lenA == 4) {
                System.out.println(strA);
                return;
            } else if (equalB && lenB == 4) {
                System.out.println(strB);
                return;
            } else {
                System.out.println("ERROR");
                return;
            }
        }
    }

    public static boolean isequalA(String str){
        String[] words=str.split(" ");
        boolean flag=true;
        for(int i=1;i<words.length;i++){
            if(!words[i].equals(words[0])){
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static boolean compareMax(String strA,String strB){
        String reg="3 4 5 6 7 8 9 10 J Q K A 2";
        char sA=strA.charAt(0);
        char sB=strB.charAt(0);
        int posA=reg.lastIndexOf(sA);
        int posB=reg.lastIndexOf(sB);
        return posA>posB;
    }

}
