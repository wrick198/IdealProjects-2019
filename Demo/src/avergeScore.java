import java.util.*;

public class avergeScore {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Float> avgScore=new ArrayList<>();
        while(sc.hasNextInt()){
            int n=sc.nextInt();
            int[] score=new int[n];
            for (int i = 0; i <n ; i++) {
                score[i]=sc.nextInt();
            }
            int maxScore=0,minScore=200;
            int sumScore=0;
            for (int i = 0; i <n ; i++) {
                sumScore+=score[i];
                if(score[i]>maxScore)
                    maxScore=score[i];
                else if(score[i]<minScore)
                    minScore=score[i];
            }
            sumScore=sumScore-maxScore-minScore;
            float avg=(float) sumScore/(n-2);
            avgScore.add(avg);
        }
        for(float entry:avgScore){
            System.out.println(String.format("%.2f",entry));
        }

    }
}
