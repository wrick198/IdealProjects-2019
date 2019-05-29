package com.steven.SpringAutoWiring;

import org.springframework.stereotype.Component;

@Component
public class SgtDreams implements CompactDisc {
    private String title="Dreams";
    private String artist="DOLF/Weird Genius/Rochelle";

    @Override
    public void play() {
        System.out.println("The disc"+title+" is played by" + artist);
    }
}
