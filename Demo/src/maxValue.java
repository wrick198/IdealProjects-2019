import java.util.*;

public class maxValue {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while(in.hasNext()) {
            int N = in.nextInt();
            int M = in.nextInt();
            int[] grades = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                grades[i] = in.nextInt();
            }

            String ch;
            int a = 0, b = 0;
            ArrayList<Integer> arr = new ArrayList<>();
            while (M > 0) {
                ch = in.next();
                a = in.nextInt();
                b = in.nextInt();
                if (ch.equals("Q")) {
                    arr.add( findMaxGrade(grades, a, b));
                } else {
                    grades[a]=b;
                }
                M--;
            }
            for (Integer grade : arr)
                System.out.println(grade);
        }
    }

    public static int findMaxGrade(int [] grades,int a,int b){
        if(a>b){
            int temp=a;
            a=b;
            b=temp;
        }

        int maxValue=0;
        for(int i=a;i<=b;i++){
            if(grades[i]>maxValue){
                maxValue=grades[i];
            }
        }
        return maxValue;
    }
}
