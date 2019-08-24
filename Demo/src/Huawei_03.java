public class Huawei_03 {
    public static void main(String[] args) {
        try {
            System.out.println("aa:"+aa());
            double d=8/5;
            System.out.println(d);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int aa() throws Exception {
        int a=1;
        for (int i = 0; i < 2; i++) {
            try {
                throw new Exception("bb");
            }catch (Exception ex){
                throw ex;
            }finally {
                return 1;
            }
        }
        return 0;
    }
}
