

public class TreeBianLi {


    /*
    验证一个序列是否是二叉树的后序遍历
     */
    public static boolean VerifySquenceOfBST(int [] sequence) {
        int len=sequence.length;
        if(len<1)
            return false;
        return VerifySquenceOfBST(sequence,0,len-1);
    }

    public static boolean VerifySquenceOfBST(int [] sequence,int low,int high) {
        if(low>=high)
            return true;
        int root=sequence[high];
        int i,j;
        for(i=low;i<high;i++){
            if(sequence[i]>root)
                break;
        }
        for(j=i;j<high;j++){
            if(sequence[j]<root){
                return false;
            }
        }
        boolean b1= VerifySquenceOfBST(sequence,0,i-1) ;
        boolean b2= VerifySquenceOfBST(sequence,i,high-1);
        return b1&&b2;
    }



    public static void main(String[] args) {
        int[] seq={1,4,2,6,3,13,14,10,8};
        System.out.println(VerifySquenceOfBST(seq));
    }
}
