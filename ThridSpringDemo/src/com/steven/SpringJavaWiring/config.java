package com.steven.SpringJavaWiring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class config {
    @Bean
    public CompactDisc compactDisc(){
        return new SgtDreams();
    }

    @Bean
    public MediaPlayer mediaPlayer(CompactDisc compactDisc){
        return new CDPlayer(compactDisc);
    }

}
