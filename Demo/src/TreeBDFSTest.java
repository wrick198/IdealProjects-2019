import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeBDFSTest {
    public static void main(String[] args) {
        int[] a={1,2,3,4,5,6,0,7};
        TreeNode root=BinaryTree.initBinaryTree(a);

        TreeBDFSTest treeBDFSTest=new TreeBDFSTest();
        treeBDFSTest.DFSTree(root);
        treeBDFSTest.BFSTree(root);
    }
    public void DFSTree(TreeNode root){
        Stack<TreeNode> nodeStack=new Stack<TreeNode>();
        nodeStack.push(root);
        while(!nodeStack.isEmpty()){
            TreeNode node=nodeStack.pop();
            System.out.println(node.val);
            if(node.right!=null)
                nodeStack.push(node.right);
            if(node.left!=null)
                nodeStack.push(node.left);
        }
    }

    public void BFSTree(TreeNode root){
        Queue<TreeNode> nodeQue=new LinkedList<>();
        nodeQue.add(root);
        while(!nodeQue.isEmpty()){
            TreeNode node=nodeQue.remove();
            System.out.println(node.val);
            if(node.left!=null)
                nodeQue.add(node.left);
            if(node.right!=null)
                nodeQue.add(node.right);
        }
    }




}
