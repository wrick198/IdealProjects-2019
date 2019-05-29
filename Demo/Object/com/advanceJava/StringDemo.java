package com.advanceJava;


public class StringDemo {
    public static void main(String[] args) {
        int [] array={12,9,15};
        int i=0;
        array[++i]=--i;
        System.out.println(array[1]);

        String helloString = new String();
        StringBuilder dd=new StringBuilder();
        System.out.println(helloString);
        System.out.println(helloString==null);
        System.out.println(helloString=="");

        float a=1.1f;
        double d=1.2;
        helloString=String.format("浮点型，%f",d);
        System.out.println(helloString);

        String str2=new String("a");
        String substr2=new String("bcf");
        System.out.println(str2.indexOf(substr2));
        System.out.println(str2.compareTo(substr2));

        String str3=new String("");
        if(str3.length()==1 || str3.length()==0)
            System.out.println(str3.length()+" ***");
        System.out.println('a'-'0');
        System.out.println('A'-'0');
        System.out.println('1');

        String str4="abcDef";
        System.out.println(str4.toLowerCase());
        System.out.println(str4.toUpperCase());

    }
}
