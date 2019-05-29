package com.steven.SpringAutoWiring;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=config.class)
public class CDPlayerTest {
    @Autowired
    private CompactDisc compactDisc;

    @Autowired
    private MediaPlayer mediaPlayer;

    @Test
    public void cdIsEmpty(){
        assertNotNull(compactDisc);
        assertNotNull(mediaPlayer);
    }

    @Test
    public void playMethod(){
        mediaPlayer.play();
    }
}
