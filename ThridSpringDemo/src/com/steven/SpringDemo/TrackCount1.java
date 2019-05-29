package com.steven.SpringDemo;

import java.util.HashMap;
import java.util.Map;


public class TrackCount1 {
    private Map<Integer,Integer> trackCount=new HashMap<>();

    public void countTrack(int tracknumber){
        int currentCount=getPlayCount(tracknumber);
        trackCount.put(tracknumber,currentCount+1);
    }

    public int getPlayCount(int num){
        return trackCount.containsKey(num)?trackCount.get(num):0;
    }
}
