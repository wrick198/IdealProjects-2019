import java.math.BigDecimal;

public class reverseInteger_LC07 {
    public int reverse(int x){
        int val=0;
        while (x!=0){
            int pop=x%10;  //取最后一位数
            x/=10;         //去掉最后以为一位数


/*            if(val>Integer.MAX_VALUE/10 || (val==Integer.MAX_VALUE/10 && pop>7))
                return 0;
            if(val<Integer.MIN_VALUE/10 || (val==Integer.MIN_VALUE/10 && pop<-8))
                return 0;*/
            double maxval=(Integer.MAX_VALUE-0L-pop)/10d;
            double minval=(Integer.MIN_VALUE-0L-pop)/10d;
            if(val>maxval)
                return 0;
            if(val<minval)
                return 0;

            int temp=val*10+pop; //将刚刚取出的数加入已有数的后面
            val=temp;
        }
        return  val;
    }

    public boolean compare(){
        double maxval=Integer.MAX_VALUE/10d;
        double minval=(Integer.MIN_VALUE-3L)/10d;
        System.out.println(maxval);
        System.out.println(minval);

        return maxval<10d;
    }

    public static void main(String[] args) {
        System.out.println((-3)%10);
        System.out.println(-3/10);

        reverseInteger_LC07 reverseInteger_lc07=new reverseInteger_LC07();
        System.out.println(reverseInteger_lc07.reverse(-123));
        System.out.println("double类型比较大小:"+reverseInteger_lc07.compare());
    }
}
