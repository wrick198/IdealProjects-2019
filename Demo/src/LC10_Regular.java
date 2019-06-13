/*
正则表达式匹配，实现“×”和“.”的功能
 */
public class LC10_Regular {
    public boolean isMatch(String s, String p) {
        if(s==null || p==null)
            return false;

        int n=s.length();
        int m=p.length();
        int i=0,j=0;
        while(i<n && j<m){
            if(s.charAt(i)==p.charAt(j)){
                i++;j++;
            }else{
                if(p.charAt(j)=='.'){ //1
                    if(j+1<m && p.charAt(j+1)=='*'){
                        j+=2;
                        if(j<m){
                            while (i<n && s.charAt(i)!=p.charAt(j))
                                i++;
                            if(i==n)
                                return false;
                        }else {
                            return true;
                        }
                    }else{
                        i++;j++;
                    }
                }else if(p.charAt(j)=='*'){  //2
                    if(i>0 && j>0 && s.charAt(i-1)==p.charAt(j-1)){
                        while(i+1<n&& s.charAt(i+1)==s.charAt(i))
                            i++;
                        i++;j++;
                        if(j<m && p.charAt(j)==p.charAt(j-2))
                            j++;
                    }else{
                        return false;
                    }
                }else{  //3
                    if(j+1<m && p.charAt(j+1)=='*'){
                        j+=2;
                    }
                    else{
                        return false;
                    }
                }
            }//end if
        }//end while
        if(i==n&&j==m)
            return true;
        else
            return false;
    }

    //递归方法
    public boolean isMatch1(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        boolean firstmatch=(!s.isEmpty() && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.'));

        if(p.length()>=2 && p.charAt(1)=='*'){
            return (firstmatch && isMatch1(s.substring(1),p)) || isMatch1(s,p.substring(2)); //两个方向进行搜索，s-1 或p-2
        }else{
            return firstmatch&&isMatch1(s.substring(1),p.substring(1));
        }
    }

    public static void main(String[] args) {
        LC10_Regular lc10_regular=new LC10_Regular();
        String s="aaab";
        String p="a*ab";
        System.out.println(lc10_regular.isMatch(s,p));
    }
}
