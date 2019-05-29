package com.steven.SpringDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy  //自动代理
public class BlankDiscConfig {
    @Bean
    public TrackCount trackCount(){
        return new TrackCount();
    }

    @Bean
    public BlankDisc blankDisc(){
        List<String> tracks = new ArrayList<String>();
        tracks.add("Sgt. Pepper's Lonely Hearts Club Band");
        tracks.add("With a Little Help from My Friends");
        tracks.add("Lucky in the Sky with Diamonds");
        tracks.add("Getting Better");
        tracks.add("Fixing a Hole");
        tracks.add("testtest");
        tracks.add("hhhhhhhhhh");

        BlankDisc bl=new BlankDisc();
        bl.setArtist("DOLF/Weird Genius/Rochelle");
        bl.setTitle("Dreams");
        bl.setTracks(tracks);
        return bl;
    }
}
