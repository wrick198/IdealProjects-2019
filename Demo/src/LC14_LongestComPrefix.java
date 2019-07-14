public class LC14_LongestComPrefix {
    public String longestCommonPrefix(String[] strs) {
        int len=strs.length;
        if(len<1)
            return "";
        if(len==1)
            return strs[0];

        int row=len;
        int mincolumn=strs[0].length();
        int l=1;
        while(l<len){
            int column=strs[l].length();
            if(column<mincolumn)
               mincolumn=column;
            l++;
        }
        if(mincolumn<1)
            return "";


        for (int i = 0; i+1 <row ;) {
            if(strs[i].charAt(0)==strs[i+1].charAt(0))
                i++;
            else
                return "";
        }
        String comprefix=String.valueOf(strs[0].charAt(0));
        for (int i = 1; i < mincolumn; i++) {
            for (int j = 0; j+1 <row ; ) {
                if(strs[j].charAt(i)==strs[j+1].charAt(i)){
                    j++;
                }
                else {
                    return comprefix;
                }
            }
            comprefix+=String.valueOf(strs[0].charAt(i));
        }
        return comprefix;
    }

    public static void main(String[] args) {
        LC14_LongestComPrefix lc14_longestComPrefix=new LC14_LongestComPrefix();
        String[] strs={"flower","flow","flight"};
        System.out.println(lc14_longestComPrefix.longestCommonPrefix(strs));
    }

}
