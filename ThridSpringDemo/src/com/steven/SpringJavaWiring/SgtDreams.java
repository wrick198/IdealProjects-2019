package com.steven.SpringJavaWiring;




public class SgtDreams implements CompactDisc {
    private String title="dreams";
    private String artist="DOLF/Weird Genius/Rochelle";

    @Override
    public void play() {
        System.out.println("The disc"+title+" is played by" + artist);
    }
}
