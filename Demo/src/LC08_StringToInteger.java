public class LC08_StringToInteger {
    //3ms
    public int myAtoi(String str){
        if(str==null){
            return 0;
        }
        int length=str.length();
        long numberint=0;
        int i=0;
        while(i<length && str.charAt(i)==' ')
            i++;
        boolean negflag=false;
        if(i<length && str.charAt(i)=='-'){ //负号
            negflag=true;
            i++;
        }else if(i<length && str.charAt(i)=='+'){ //正号
            i++;
        }
        for (; i < length; i++) {
            int a=str.charAt(i)-'0'; //转换为数字
            if(a<0 || a>9){  //出现字母
                return Integer.parseInt(String.valueOf(numberint));
            }else if(a>=0 && a<=9){ //数字
                if(negflag)
                    a=a*(-1);
                long temp=numberint*10+a;
                numberint=temp;
                if(numberint>Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                else if(numberint<Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
            }
        }//end for
        return Integer.parseInt(String.valueOf(numberint));
    }


    //1ms,避免使用long类型，再将long转换为int，需要花费更多时间。最优算法。
    public int myAtoi1(String str) {
        if(str==null){
            return 0;
        }
        int length=str.length();
        int numberint=0;
        int i=0;
        while(i<length && str.charAt(i)==' ')
            i++;
        boolean negflag=false;
        if(i<length && str.charAt(i)=='-'){ //负号
            negflag=true;
            i++;
        }else if(i<length && str.charAt(i)=='+'){ //正号
            i++;
        }
        for (; i < length; i++) {
            int a=str.charAt(i)-'0'; //转换为数字
            if(a<0 || a>9){  //出现字母
                return numberint;
            }else if(a>=0 && a<=9){ //数字
                if(negflag)
                    a=a*(-1);
                if(numberint>Integer.MAX_VALUE/10 || (numberint==Integer.MAX_VALUE/10 && a>7)) //比最大值大,提前判断计算结果的值
                    return Integer.MAX_VALUE;
                if(numberint<Integer.MIN_VALUE/10 || (numberint==Integer.MIN_VALUE/10 && a<-8)) //比最大值小
                    return Integer.MIN_VALUE;
                numberint=numberint*10+a;

            }
        }//end for
        return numberint;
    }

    public static void main(String[] args) {
        LC08_StringToInteger lc08_stringToInteger=new LC08_StringToInteger();
        System.out.println(lc08_stringToInteger.myAtoi1(" -42 w"));
    }
}
