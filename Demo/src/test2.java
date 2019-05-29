

public class test2 {
    static int score;
    static Integer score2;
    public static void main(String[] args) {
        String a = "AAA";
        String b ="AAA";
        int hashcode=a.hashCode();
        System.out.println(hashcode>>>16);
        System.out.println(4^2);
        hashcode=hashcode^(hashcode>>>16);
        System.out.println(hashcode);
        System.out.println(b.hashCode());


        System.out.println("int类型的默认值score2:" + score);
        System.out.println("Integer类型的默认值score:" + score2);

        String str="abcdef";
        String substr=str.substring(0,2);
        System.out.println(str);
        System.out.println(substr);

    }
}
