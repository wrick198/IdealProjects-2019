package com.steven.NetEase;

import java.util.*;

public class Main {
    public  void method(){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<String,Integer> hostId=new LinkedHashMap<>(n);
        Map<Integer,String> logText=new LinkedHashMap<>(n);

        ArrayList<String> result=new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            String str=sc.next();
            String[] line=str.split(" ");
            hostId.put(line[1],i);
            logText.put(i,line[2]);
        }

        int m=sc.nextInt();
        for (int i = 0; i <m ; i++) {
            int k=sc.nextInt(); //k个参数
            boolean flag=false;
            String search=new String();
            String hostid=new String();
            int before=Integer.MAX_VALUE;
            int after=Integer.MAX_VALUE;

            for (int j = 0; j < k; j++) {
                String str=sc.next();
                String[] line=str.split(" ");
                if(line[0].equals("--search")) {
                    search=line[1];
                }
                if(line[0].equals("--hostid")) {
                    flag = true;
                    hostid=line[1];
                }
                if(line[0].equals("--before")) {
                    before=Integer.parseInt(line[1]);
                }
                if(line[0].equals("--after")) {
                    after=Integer.parseInt(line[1]);
                }
            }
            if(flag){
                int value=hostId.get(hostid);
                String str=logText.get(value);
                if(violentMatch(str,search)!=-1){
                    int j=value;
                    int begin=j,end=j;
                    if(before<Integer.MAX_VALUE){
                        begin=j-before;
                    }
                    if(after<Integer.MAX_VALUE){
                        end=j+after;
                    }
                    if(begin<0)
                        begin=0;
                    if(end>=n)
                        end=n-1;
                    for (int l = begin; l <=end ; l++) {
                        result.add(logText.get(l));
                    }
                }
                else{
                    result.add("ERROR");
                }
            }
            else{
                boolean flag1=false;
                for (int j = 0; j <n ; j++) {
                    if(violentMatch(logText.get(j),search)!=-1){
                        flag1=true;
                        int begin=j,end=j;
                        if(before<Integer.MAX_VALUE){
                            begin=j-before;
                        }
                        if(after<Integer.MAX_VALUE){
                            end=j+after;
                        }
                        if(begin<0)
                            begin=0;
                        if(end>=n)
                            end=n-1;
                        for (int l = begin; l <=end ; l++) {
                            result.add(logText.get(l));
                        }
                    }
                }
                if(!flag1)
                    result.add("ERROR");
            }
        }

        for (String sre:result)
            System.out.println(sre);

    }

    public int violentMatch(String ss, String ps) {
        char[] s = ss.toCharArray();
        char[] p = ps.toCharArray();

        int i = 0; // 主串的位置
        int j = 0; // 模式串的位置
        while (i < s.length && j < p.length) {
            if (s[i] == p[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Main ma=new Main();
        ma.method();
    }

}
