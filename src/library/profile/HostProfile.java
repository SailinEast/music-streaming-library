package library.profile;

import library.media.Podcast;
import library.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HostProfile {
    // Fields
    private final User user;
    private List<Podcast> podcasts = new ArrayList<>();

    // Constructor
    public HostProfile(User user) {
        this.user = user;
    }

    // Getters
    public User getUser() {
        return user;
    }

    public List<Podcast> getPodcasts() {
        return Collections.unmodifiableList(podcasts);
    }

    // Methods
    public void addPodcast(Podcast podcast) {
        podcasts.add(podcast);
    }

    @Override
    public String toString() {
        String podcastTitles = podcasts.stream().map(p -> "'" + p.getTitle() + "'")
                .collect(Collectors.joining(", ", "[", "]"));
        return "HostProfile{handle='" + user.getHandle() +
                "', podcasts=" + podcastTitles +
                "}";
    }
}