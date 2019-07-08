import javax.print.attribute.standard.PageRanges;

public class test1 {

}



class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

class Solution {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead==null)
            return null;
        RandomListNode pNode=pHead;
        while(pNode!=null){ //结点的复制
            RandomListNode cNode=new RandomListNode(pNode.label);
            cNode.next=pNode.next;
            pNode.next=cNode;
            pNode=cNode.next;
        }

        pNode=pHead;
        while(pNode!=null){ //复制结点的任意指向
            RandomListNode cNode=pNode.next;
            cNode.random=(pNode.random!=null)?pNode.random.next:null;
            pNode=cNode.next;
        }

        pNode=pHead;
        RandomListNode cHead=pHead.next;
        RandomListNode cNode=cHead;
        pNode.next=cNode.next; //链表拆分
        pNode=pNode.next;
        while(pNode!=null){
            cNode.next=pNode.next;
            cNode=cNode.next;
            pNode.next=cNode.next;
            pNode=pNode.next;
        }
        return cHead;
    }
}
