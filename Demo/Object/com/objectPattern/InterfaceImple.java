package com.objectPattern;

interface Monster{
    void menace();
}

interface DangerousMonser extends Monster{
    void destroy();
}

class DragonZila implements DangerousMonser{
    public void menace(){
        System.out.println("DragonZila menace");
    }

    public void destroy(){
        System.out.println("DragonZila destroy");
    }
}

public class InterfaceImple {


    public static void main(String[] args) {
        DangerousMonser barbey=new DragonZila();
        barbey.destroy();
        barbey.menace();
    }
}
