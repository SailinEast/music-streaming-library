package com.sail.musiclibrary.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class Media implements MediaPlayer {

    @Column(nullable = false)
    @Getter @Setter protected String title;
    @Getter @Setter protected int duration;

    @Override
    public void play() {
        System.out.println("Playing media: " + this.title);
    }
}
