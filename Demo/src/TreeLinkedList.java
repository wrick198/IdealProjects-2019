import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeLinkedList {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null)
            return null;

        TreeNode head=new TreeNode(0);
        TreeNode h=head;
        TreeNode p=pRootOfTree;
        Stack<TreeNode> stack=new Stack<>();
        h.left=null;
        while (!stack.isEmpty() || p!=null){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else{
                TreeNode node=stack.pop();
                h.right=node;
                node.left=h;
                h=node;
                p=node.right;
            }
        }
        h.right=null;
        pRootOfTree=head.right;
        pRootOfTree.left=null;
        return pRootOfTree;
    }

    public TreeNode Convert1(TreeNode pRootOfTree) {
        if(pRootOfTree==null)
            return null;

        TreeNode p=pRootOfTree;
        Stack<TreeNode> stack=new Stack<>();
        Queue<TreeNode> queue=new LinkedList<>();
        while (!stack.isEmpty() || p!=null){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else{
                TreeNode node=stack.pop();
                queue.add(node); //中序遍历，使用队列来存储遍历的结点
                p=node.right;
            }
        }
        pRootOfTree=p=queue.remove();
        p.left=null;
        while(!queue.isEmpty()){
            TreeNode node=queue.remove();
            node.left=p;
            p.right=node;
            p=node;
        }
        p.right=null;
        return pRootOfTree;
    }

}
