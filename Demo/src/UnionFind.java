import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//并查集
//参考：http://acm.hdu.edu.cn/showproblem.php?pid=1232
public class UnionFind {
    private int[] weigh;
    private int[] parent;
    private int N;
    private int groupSize;

    public UnionFind(int n){
        N=n;
        groupSize=n;
        parent=new int[N];
        weigh=new int[N];
        for (int i = 0; i < N; i++) {
            parent[i]=i;
            weigh[i]=1;
        }
    }

    public int findGroup(int p){
        while(parent[p]!=p){
            parent[p]=parent[parent[p]];
            p=parent[p];
        }
        return p;
    }

    public boolean isConnect(int p,int q){
        return findGroup(p)==findGroup(q);
    }

    public void union(int p,int q){
        int firstG=findGroup(p);
        int secondG=findGroup(q);
        if(firstG==secondG)
            return;

        if(weigh[firstG]>weigh[secondG]){
            parent[secondG]=firstG;
        }else if(weigh[firstG]<weigh[secondG]){
            parent[firstG]=secondG;
        }else{
            parent[secondG]=firstG;
            weigh[firstG]+=weigh[secondG];
        }
        this.groupSize--;
    }

    public int getGroupSize(){
        return this.groupSize;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Integer> num=new ArrayList<>();
        while(sc.hasNextInt()){
            int N=sc.nextInt();
            if(N==0)
                break;
            int M=sc.nextInt();
            if(M==0){
                num.add(N-1);
                continue;
            }

            UnionFind unionFind=new UnionFind(N);
            for (int i = 0; i < M; i++) {
                unionFind.union(sc.nextInt()-1,sc.nextInt()-1);
            }
            num.add(unionFind.getGroupSize()-1);
        }

        for (Integer i:num){
            System.out.println(i);
        }
    }

}
