public class Huawei_exam_20190730 {



    public  Huawei_exam_20190730(){

    }

    static int arr[]=new int[10];
    public static void main(String[] args) {
        String s=new String("H");

        System.out.println(s=="H");
        System.out.println(s.equals("H"));
        System.out.println("H"==new String("H"));

        int a[]=new int[10];
        int b[];
        System.out.println(a.length);

        System.out.println(arr[1]);



        int t=0;
        System.out.println("d"+t);

        Integer s2=new Integer(9);
        System.out.println(s2.equals(new Integer(9)));


        modeify(a1);
        System.out.println(a1);

        float f[][]=new float[6][];

        Float f12=new Float(2);
        int a2=2;
        switch (a2){
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
                default:
                    System.out.println(4);
        }
        System.out.println(Math.round(111.6));
    }
    private static int a1;

    static void modeify(int a){
        a++;
    }
}
