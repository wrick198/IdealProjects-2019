
import java.util.*;

public class guessNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 4;
        while (in.hasNextInt()) {
            int[] anser = new int[n];
            int[] gs = new int[n];
            for (int i = 0; i < n; i++) {
                anser[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++){
                gs[i] = in.nextInt();
            }
            int a = 0;
            int b = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (gs[j] == anser[i]) {
                        if (j == i)
                            a++;
                        else
                            b++;
                    }
                }
            }
            System.out.println(a + "A" + b + "B");
        }
    }
}