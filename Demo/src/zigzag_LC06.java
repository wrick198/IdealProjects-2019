public class zigzag_LC06 {
    public String convert(String s,int numRows){
        if(s==null || numRows<1)
            return null;
        if(s.length()==1 || numRows==1)
            return s;

        int length=s.length();
        StringBuilder strconvert=new StringBuilder();
        int val=2*numRows-2; //每行间距
        for (int i = 0; i < numRows; i++) {  //从行开始读
            for (int j = 0; j+i <length ; j+=val) {  //读完一整行
                strconvert.append(s.charAt(i+j)); //i+j为行的位序
                if(i!=0 && i!=numRows-1 && (i+j+val-2*i)<length && (i+j+val-2*i)>0 ) //除了第一行和最后一行，每一行的间距为val的列与前面一个数的间距为 2 4 6 8
                    strconvert.append(s.charAt(i+j+val-2*i));
            }
        }
        return strconvert.toString();
    }

    public static void main(String[] args) {
        String s="PAYPALISHIRING";
        zigzag_LC06 zigzag_lc06=new zigzag_LC06();
        System.out.println(zigzag_lc06.convert(s,4));
    }
}
