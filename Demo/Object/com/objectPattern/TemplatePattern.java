package com.objectPattern;

abstract class Game {
    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}

class FootBall extends Game {
    @Override
    void initialize() {
        System.out.println("football game iniialized: start playing");
    }

    @Override
    void startPlay() {
        System.out.println("the football game has started");
    }

    @Override
    void endPlay() {
        System.out.println("football game finished");
    }
}

class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket game iniialized: start playing");
    }

    @Override
    void startPlay() {
        System.out.println("the Cricket game has started");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket game finished");
    }
}

public class TemplatePattern {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
    }

}
