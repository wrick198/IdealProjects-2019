public class Power {

    public double Power(double base, int exponent) {
        if(base==0)
            return 0.0;
        if(exponent==0)
            return 1;
        boolean flag=false;
        if(exponent<0) {
            exponent = 0 - exponent;
            flag=true;
        }
        double result=1.0;
        while(exponent>0){
            result*=base;
            exponent--;
        }
        if(flag)
            result=1/result;
        return result;
    }

    public static void reOrderArray(int [] array) {
        int len=array.length;
        int[] temparray=new int[len];
        int i,j=0;
        for(i=0;i<len;i++){
            if(array[i]%2==1)
            {
                temparray[j]=array[i];
                j++;
            }
        }
        for(i=0;i<len;i++){
            if(array[i]%2==0){
                temparray[j]=array[i];
                j++;
            }
        }
        for (int k = 0; k < len; k++) {
            array[k]=temparray[k];
        }
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        if(array==null || array.length<1)
            return 0;
        int i=0;
        int negSub=Integer.MIN_VALUE;
        while( i<array.length && array[i]<0) {
            if(array[i]>negSub)
                negSub=array[i];
            i++;
        }
        if(i==array.length)
            return negSub;
        int maxSub=Integer.MIN_VALUE;
        int current=0;
        for (; i < array.length; i++) {
            current+=array[i];
            if(current<0) {
                current = 0;
            }
            else if(current>maxSub){
                maxSub=current;
            }
        }
        return maxSub;
    }

    public static void main(String[] args) {
        double base=0.0;
        System.out.println(base==0);
        int[] arr={1,2,3,4,5};
        System.out.println(arr);

        reOrderArray(arr);
        System.out.println(arr);

        Byte a=127;
        Byte b=126;
        Byte c;
        c=(byte)(a+b);
        System.out.println(c);

        int d=Integer.MAX_VALUE;
        int e=d+1;
        System.out.println(e);
    }
}
