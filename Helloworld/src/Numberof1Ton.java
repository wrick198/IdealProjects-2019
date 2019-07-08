public class Numberof1Ton {
    public int NumberOf1Between1AndN_Solution(int n) {
        if(n<1)
            return 0;
        int count=0;
        for (int i = 0; i <=n ; i++) {
            count+=Numberof1(i);
        }
        return count;
    }

    private int Numberof1(int m){
        int count=0;
        while(m!=0){
            if(m%10==1)
                count++;
            m=m/10;
        }
        return count;
    }
}
