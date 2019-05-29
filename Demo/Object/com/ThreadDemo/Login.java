package com.ThreadDemo;

class LoginServlet {
    private static String username;
    private static String password;

    public static void doPost(String user, String pass) {
        try {
            username = user;
            if (username.equals("a"))
                Thread.sleep(5000);
            password = pass;
            System.out.println(username + " " + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Alogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("a", "aaa");
    }
}

class Blogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("b", "bbb");
    }
}

public class Login {
    public static void main(String[] args) {
        Alogin alogin = new Alogin();
        alogin.setName("a");
        Blogin blogin = new Blogin();
        blogin.setName("b");
        alogin.start();
        blogin.start();
    }
}
