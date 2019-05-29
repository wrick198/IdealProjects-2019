import jdk.nashorn.api.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val ;
    TreeNode left ;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

class BinaryTree{
    public static TreeNode initBinaryTree(int[] values){
        if(values==null)
            return null;
        return initBinaryTree(values,0);
    }
    private static TreeNode initBinaryTree(int[] values,int headInd){
        if(values[headInd]==0)
            return null;

        TreeNode root=new TreeNode(values[headInd]);
        int leftInd=headInd*2+1;
        int rightInd=headInd*2+2;
        int len=values.length-1;
        if(leftInd>len){
            root.left=null;
        }else{
            root.left=initBinaryTree(values,leftInd);
        }
        if(rightInd>len){
            root.right=null;
        }else{
            root.right=initBinaryTree(values,rightInd);
        }
        return root;
    }
}


public class SubTree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean result=false;
        if(root1==null || root2==null)
            return false;

        if(root1.val==root2.val)
            result=isSubTree(root1,root2);
        if(!result)
            result=HasSubtree(root1.left,root2);
        if(!result)
            result=HasSubtree(root1.right,root2);
        return result;
    }

    private boolean isSubTree(TreeNode root1, TreeNode root2){
        if(root2==null)
            return true;
        if(root1==null)
            return false;
        if(root1.val!=root2.val)
            return false;

        return isSubTree(root1.left,root2.left)&&isSubTree(root1.right,root2.right);
    }

    public static void main(String[] args) {
        int[] values={8,8,7,9,2,0,0,0,0,4,7};
        int[] subvalues={8,9,3};
        TreeNode ftree=BinaryTree.initBinaryTree(values);
        TreeNode subtree=BinaryTree.initBinaryTree(subvalues);

        SubTree subTree=new SubTree();
        System.out.println(subTree.HasSubtree(ftree,subtree));
    }
}
