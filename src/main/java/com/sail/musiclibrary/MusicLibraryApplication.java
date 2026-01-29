package com.sail.musiclibrary;

import com.sail.musiclibrary.artist.ArtistService;
import com.sail.musiclibrary.user.User;
import com.sail.musiclibrary.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserService userService,
                                    ArtistService artistService) {
        return args -> {
            System.out.println("--- APP INITIALIZED ---");

            User u1 = userService.createUser("Bob");
            artistService.promote(u1.getId(), "I'm bob hello");
            System.out.println("Artist '" + u1.getHandle() + "' with ID: " + u1.getId() + " created.");

            System.out.println("--- DONE ---");
        };
    }
}
