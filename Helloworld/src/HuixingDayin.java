import java.util.ArrayList;

public class HuixingDayin {
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        if(matrix==null)
            return null;
        ArrayList<Integer> arr=new ArrayList<>();
        int n=matrix[0].length;
        int m=matrix.length;
        int nL=0,mL=0;
        int i=0,j=0;
        while(nL<n && mL<m){
            while(j<n){
                arr.add(matrix[i][j]);
                j++;
            }
            i++;j--;mL++;
            if(i>=m)
                break;
            while(i<m){
                arr.add(matrix[i][j]);
                i++;
            }
            j--;i--;n--;
            if(j<0)
                break;
            while(j>=nL){
                arr.add(matrix[i][j]);
                j--;
            }
            i--;j++;m--;
            while(i>=mL){
                arr.add(matrix[i][j]);
                i--;
            }
            j++;i++;nL++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] matrix={{1,2,3,4},{5,6,7,8}};
        int[][] matrix1={{1},{2},{3},{4},{5}};
        printMatrix(matrix1);
    }
}
