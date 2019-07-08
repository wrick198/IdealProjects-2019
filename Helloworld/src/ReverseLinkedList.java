
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class ReverseLinkedList {
    public ListNode ReverseList(ListNode head) {
        if(head==null)
            return null;
        ListNode pre=null,next=null;
        while(head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode p1=new ListNode(1);
        ListNode p2=new ListNode(2);
        ListNode p3=new ListNode(3);
        p1.next=p2;
        p2.next=p3;

        ReverseLinkedList re=new ReverseLinkedList();
        re.ReverseList(p1);
    }
}