
import java.util.*;
public class jumpMethod {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Integer> method=new ArrayList<>();
        while(sc.hasNextInt()){
            int L=sc.nextInt();
            int S=sc.nextInt();
            int T=sc.nextInt();
            int M=sc.nextInt();
            List<Integer> stone=new ArrayList<>();
            for (int i = 0; i <M ; i++) {
                stone.add(sc.nextInt());
            }
            int me=findMinMethod(L,S,T,stone);
            System.out.println(me);
        }
    }

    public static int findMinMethod(int L,int S,int T,List<Integer> stone){
        List<Integer> method=new ArrayList<>();
        for(int i=T;i>=S;i--){
            if(stone.contains(i))
                method.add(findMethod(i,L,stone,S,T));
        }
        int minMethod=L;
        for(Integer entry:method){
            if(entry<minMethod)
                minMethod=entry;
        }
        return minMethod;
    }

    public static int findMethod(int pos,int L,List<Integer> stone,int S,int T){
        int sum=0;
        if(pos>=L)
            return 0;

        while(pos<L){
            int min=L;
            for(int i=T;i>=S;i--){
                if(stone.contains(pos+i)) {
                    sum++;
                    sum+=findMethod(pos + i, L, stone, S, T);
                }
                else if((pos+i)>L){
                    sum++;
                    return 0;
                }
            }

            break;
        }
        return sum;
    }
}
