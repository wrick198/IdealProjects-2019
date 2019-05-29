import java.util.*;

public class test1 {
    public static void main(String[] args) {
//        Set<String> hs=new HashSet<>();
//        Set<String> hs=new LinkedHashSet<>();
        System.out.println("Set集合演示：");
        Set<String> hs = new TreeSet<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o2.compareTo(o1);
                    }
                }
        );
        hs.add("c");
        hs.add("b");
        hs.add("a");
        hs.add("d");
        System.out.println(hs.contains("abc"));
        for (String s : hs)
            System.out.println(s);

        System.out.println("LinkedList示例：");
        LinkedList<String> linklt = new LinkedList<>();
        linklt.add("a");
        linklt.add("b");
        linklt.addLast("c");
        linklt.addFirst("000");
        linklt.add("d");
        System.out.println(linklt);
        linklt.remove("b");
        System.out.println(linklt);

        System.out.println("栈演示示例：");
        Stack<Integer> stack=new Stack<>();
        System.out.println(stack.size()+" "+stack.capacity());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
//        System.out.println(stack.peek());
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
        for(Integer i:stack)
            System.out.println(i);
        System.out.println(stack.size()+" "+stack.capacity());

        System.out.println("队列演示示例：");
        Queue<String> queue=new LinkedList<>();
        queue.offer("abc");
        queue.offer("abc1");
        queue.offer("abc2");
        System.out.println("出队："+queue.poll());
        for(String s:queue)
            System.out.println(s);

    }
}