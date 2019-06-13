/*
水仙花数
 */
public class LC09_palindrome {
    public boolean isPalindrome(int x) {
        String str=String.valueOf(x);
        int length=str.length();
        int begin=0;
        int end=length-1;
        while(begin<end){
            if(str.charAt(begin)==str.charAt(end)){
                begin++;
                end--;
            }else{
                return false;
            }
        }
        return true;
    }

    //最优算法
    public boolean isPalindrome1(int x) {
        if(x<0 || (x!=0 && x%10==0))
            return false;
        int reversnum=0;
        while(x>reversnum){
            reversnum=reversnum*10+x%10;
            x/=10;
        }
        return x==reversnum || x==reversnum/10;
    }
}
