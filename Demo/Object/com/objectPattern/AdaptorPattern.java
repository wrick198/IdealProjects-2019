package com.objectPattern;

interface AdvanceMediaPlayer {
    void playVlc(String fileName);

    void playMp4(String fileName);
}

class VlcPlayer implements AdvanceMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("play vlc"+ fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}

class Mp4Player implements AdvanceMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("play mp4"+fileName);
    }
}

interface MediaPlayer {
    void play(String audioType, String fileName);
}

class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equals("mp3")){
            System.out.println("play mp3, 文件为:"+fileName);
        }
        else if(audioType.equals("vlc")|| audioType.equals("mp4")){
            mediaAdapter=new MediaAdapter();
            mediaAdapter.play(audioType,fileName);
        }
        else{
            System.out.println("不支持的播放类型!");
        }
    }
}

class MediaAdapter implements MediaPlayer{
    AdvanceMediaPlayer advanceMediaPlayer;
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equals("vlc")) {
            advanceMediaPlayer=new VlcPlayer();
            advanceMediaPlayer.playVlc(fileName);
        }
        else if(audioType.equals("mp4")) {
            advanceMediaPlayer=new Mp4Player();
            advanceMediaPlayer.playMp4(fileName);
        }
    }
}

public class AdaptorPattern {
    public static void main(String[] args) {
        String audioType="mp4";
        String fileName="可能否.mp4";
        AudioPlayer audioPlayer=new AudioPlayer();
        audioPlayer.play(audioType,fileName);
    }
}
