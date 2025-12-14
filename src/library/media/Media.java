package library.media;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Media {
    private String title;
    private final int duration;
    private final LocalDate releaseDate;
    private final DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Media(String title, int duration) {
        this.title = Objects.requireNonNull(title, "Title cannot be null!");
        this.duration = duration;
        releaseDate = LocalDate.now(ZoneId.of("Asia/Almaty"));
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getReleaseDate() {
        return releaseDate.format(formattedDate);
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
}