
public class MaopaoSort {
    public static void main(String[] args) {
        int[] a={3,100,2,1,4};
        sortfun(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

    public static void sortfun(int[] a){
        int n=a.length-1;
        for(int i=n;i>=0;i--){
            for (int j = 0; j < i; j++) {
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
    }
}
