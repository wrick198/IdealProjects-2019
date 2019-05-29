
import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.List;


public class LujinSum {
    ArrayList<ArrayList<Integer>> arr=new  ArrayList<ArrayList<Integer>>();
    private int currentVal;

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root==null)
            return null;

        FindPath1(root,target,new ArrayList<Integer>());
        return arr;
    }

    private void FindPath1(TreeNode root,int target,List<Integer> path) {
        path.add(root.val);
        currentVal+=root.val;
        if(root.left==null && root.right==null && currentVal==target) {
            arr.add(new ArrayList(path));
        }else{
            if(root.left!=null)
               FindPath1(root.left,target,path);
            if(root.right!=null)
                FindPath1(root.right,target,path);
        }
        path.remove(path.size()-1);
        currentVal-=root.val;
    }

    public static void main(String[] args) {
        int[] a={4,4,7,3};
        TreeNode root=BinaryTree.initBinaryTree(a);

        LujinSum lujinSum=new LujinSum();
        lujinSum.FindPath(root,11);
    }
}