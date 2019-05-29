package com.steven.SpringDemo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class TrackCount {
    private Map<Integer,Integer> trackCount=new HashMap<>();

    @Pointcut("execution(* com.steven.SpringDemo.BlankDisc.playTrack(..)) && args(tracknumber)")
    public void trackPlayed(int tracknumber){}

    @Before("trackPlayed(tracknumber)")
    public void countTrack(int tracknumber){
        int currentCount=getPlayCount(tracknumber);
        trackCount.put(tracknumber,currentCount+1);
    }

    public int getPlayCount(int num){
        return trackCount.containsKey(num)?trackCount.get(num):0;
    }
}
