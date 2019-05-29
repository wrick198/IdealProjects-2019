package com.objectPattern;

interface Selector{
    boolean end();
    Object current();
    void next();
}

public class Sequence {
    private Object[] items;
    private int next=0;
    public Sequence(int size){
        items=new Object[size];
    }
    public void add(Object x){
        if(items.length>next)
            items[next++]=x;
    }



    private class SequenceSeletor implements Selector{
        private int i=0;

        @Override
        public boolean end() {
            return i==items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if(i<items.length)
                i++;
        }

        public void reverseSelector(){
            int j=items.length-1;
            while(j>=0){
                System.out.println(items[j--]);
            }
        }
    }

    public static void main(String[] args) {
        Sequence sequence=new Sequence(10);
        for (int i = 0; i <10 ; i++) {
            sequence.add(i);
        }

        Sequence.SequenceSeletor sequenceSeletor=sequence.new SequenceSeletor();
        while(!sequenceSeletor.end()){
            System.out.println(sequenceSeletor.current());
            sequenceSeletor.next();
        }
        sequenceSeletor.reverseSelector();
    }
}
