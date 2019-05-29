package com.steven.SpringDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BlankDiscConfig.class)
public class BlankDiscTest {
    @Autowired
    private BlankDisc blankDisc;
    @Autowired
    private TrackCount trackCount;

    @Test
    public void testTrackCount(){
        blankDisc.playTrack(0);
        blankDisc.playTrack(1);
        blankDisc.playTrack(2);
        blankDisc.playTrack(2);
        blankDisc.playTrack(2);
        blankDisc.playTrack(2);
        blankDisc.playTrack(6);
        blankDisc.playTrack(6);

        assertEquals(1, trackCount.getPlayCount(0));
        assertEquals(1, trackCount.getPlayCount(1));
        assertEquals(4, trackCount.getPlayCount(2));
        assertEquals(0, trackCount.getPlayCount(3));
        assertEquals(0, trackCount.getPlayCount(4));
        assertEquals(0, trackCount.getPlayCount(5));
        assertEquals(2, trackCount.getPlayCount(6));
    }
}
