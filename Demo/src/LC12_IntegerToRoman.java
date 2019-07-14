
import java.util.*;

class roman
{
    int val;
    String s;

    public roman(int v,String str)
    {
        val = v;
        s = str;
    }
}

public class LC12_IntegerToRoman {

    public String intToRoman1(int num) {
        if(num<1 || num>3999)
            return "error, not in range";

        StringBuilder romanstr=new StringBuilder();
        Map<Integer,String> romanmap=new HashMap<>();
        romanmap.put(1,"I");
        romanmap.put(4,"IV");
        romanmap.put(5,"V");
        romanmap.put(9,"IX");
        romanmap.put(10,"X");
        romanmap.put(40,"XL");
        romanmap.put(50,"L");
        romanmap.put(90,"XC");
        romanmap.put(100,"C");
        romanmap.put(400,"CD");
        romanmap.put(500,"D");
        romanmap.put(900,"CM");
        romanmap.put(1000,"M");
        Integer[] roman={1,4,5,9};
        List<Integer> romanlist=new ArrayList<Integer>(Arrays.asList(roman));

        int pos=1; //数的数量级
        while (num>0){
            int a=num%10;  //取个位数
            num=num/10;
            if(romanlist.contains(a)){
                romanstr.insert(0,romanmap.get(a*pos));
            }else if(a>5){
                a=a-5;
                StringBuilder str=new StringBuilder(romanmap.get(5*pos));
                while(a>0){
                    str.append(romanmap.get(1*pos));
                    a--;
                }
                romanstr.insert(0,str);
            }else{
                while(a>0){
                    romanstr.insert(0,romanmap.get(1*pos));
                    a--;
                }
            }
            pos*=10;
        }
        return new String(romanstr);
    }

    //位数确定，从后往前找
    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        ArrayList<roman> a  = new ArrayList<roman>();
        a.add(new roman(1000,"M"));
        a.add(new roman(900,"CM"));
        a.add(new roman(500,"D"));
        a.add(new roman(400,"CD"));
        a.add(new roman(100,"C"));
        a.add(new roman(90,"XC"));
        a.add(new roman(50,"L"));
        a.add(new roman(40,"XL"));
        a.add(new roman(10,"X"));
        a.add(new roman(9,"IX"));
        a.add(new roman(5,"V"));
        a.add(new roman(4,"IV"));
        a.add(new roman(1,"I"));

        int no;
        for(int i=0;i<a.size();i++)
        {
            no = num/(a.get(i).val);   //取最高位的数，除1000
            if(no!=0)
            {
                for(int j=0;j<no;j++)
                {
                    ans.append(a.get(i).s);
                }

            }
            num = num % a.get(i).val;   //对1000取余
        }

        return ans.toString();
    }

    /*
    罗马符号转化为数字
     */
    public int romanToInt(String s) {
        if(s==null)
            return -1;
        Map<String,Integer> romanmap=new HashMap<>();
        romanmap.put("I",1);
        romanmap.put("IV",4);
        romanmap.put("V",5);
        romanmap.put("IX",9);
        romanmap.put("X",10);
        romanmap.put("XL",40);
        romanmap.put("L",50);
        romanmap.put("XC",90);
        romanmap.put("C",100);
        romanmap.put("CD",400);
        romanmap.put("D",500);
        romanmap.put("CM",900);
        romanmap.put("M",1000);

        int n=s.length();
        int i=0;
        int romanint=0;
        while(i<n){
            String substr;
            if(i+2<=n)
                substr=s.substring(i,i+2);
            else
                substr=s.substring(i,n);
            if(romanmap.containsKey(substr)){
               romanint+=romanmap.get(substr);
               i+=2;
            }else{
                substr=s.substring(i,i+1);
                romanint+=romanmap.get(substr);
                i++;
            }
        }
        return romanint;
    }

    public static void main(String[] args) {
        LC12_IntegerToRoman lc12_integerToRoman=new LC12_IntegerToRoman();
        System.out.println(lc12_integerToRoman.romanToInt("MCMXCIV"));
    }
}






