import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueDemo {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        int lenA=pushA.length;
        int lenB=popA.length;
        if(lenA!=lenB || lenA<1||lenB<1)
            return false;
        Stack<Integer> que=new Stack<>();
        int i=0,j=0;
        for(;i<lenA;i++) {
            que.push(pushA[i]);
            while(!que.isEmpty() && que.peek()==popA[j]){
                que.pop();
                j++;
            }
        }
        return que.isEmpty();
    }
}

