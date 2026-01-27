package com.sail.musiclibrary.common;

import jakarta.persistence.*;
import lombok.Getter;

@MappedSuperclass
public abstract class Media implements MediaPlayer {

    @Column(nullable = false)
    @Getter protected String title;
    @Getter protected int duration;

    @Override
    public void play() {
        System.out.println("Playing media: " + this.title);
    }
}
