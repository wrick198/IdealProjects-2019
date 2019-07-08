

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class LinklistPaixu {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        ListNode head=null,p=null;
        if(list1.val>list2.val){
            p=list2;
            list2=list2.next;
        }
        else{
            p=list1;
            list1=list1.next;
        }
        head=p;
        while(list1!=null && list2!=null){
            if(list1.val>list2.val){
                p.next=list2;
                list2=list2.next;
                p=p.next;
            }
            else{
                p.next=list1;
                list1=list1.next;
                p=p.next;
            }
        }
        if(list1==null){
            p.next=list2;
        }
        if(list2==null){
            p.next=list1;
        }
        return head;
    }
}