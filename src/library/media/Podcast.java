package library.media;

import library.profile.HostProfile;

public class Podcast extends Media implements Player {
    // Fields
    private HostProfile host;
    private String description;

    // Constructor
    public Podcast(String title, int duration, HostProfile host) {
        super(title, duration);
        this.host = host;
        host.addPodcast(this);
    }

    // Getters
    public HostProfile getHost() {
        return host;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Podcast{title='" + getTitle() +
                "', duration=" + getDuration() +
                ", description='" + description +
                "', host='" + host.getUser().getHandle() +
                "'}";
    }

    @Override
    public void play() {
        System.out.println("Playing podcast " + getTitle());
    }
}
