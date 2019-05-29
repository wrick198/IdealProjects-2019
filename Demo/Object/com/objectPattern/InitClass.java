package com.objectPattern;

import java.io.*;


public class InitClass implements Serializable,Cloneable {
    private  int id;
    public InitClass(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) throws Exception {
        InitClass initClass=new InitClass(741);
        System.out.println(initClass.getId());

        ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("initClass.bin"));
        output.writeObject(initClass);
        output.close();

        ObjectInputStream input=new ObjectInputStream(new FileInputStream("initClass.bin"));
        InitClass init1=(InitClass)input.readObject(); //序列化对象
        init1.setId(134);
        System.out.println(initClass.getId());

        InitClass init2=(InitClass)initClass.clone();  //克隆对象
        init2.setId(852);
        System.out.println(initClass.getId());
    }


}
