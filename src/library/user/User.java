package library.user;

import library.profile.ArtistProfile;
import library.profile.HostProfile;
import library.profile.ListenerProfile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {
    private final String handle;
    private String displayName;
    private final LocalDate userCreationDate; // LocalDate class that stores date
    private final DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // to format
    private final ListenerProfile listenerProfile;
    private HostProfile hostProfile = null;
    private ArtistProfile artistProfile = null;

    // Constructors
    public User(String handle) {
        this.handle = Objects.requireNonNull(handle.toLowerCase(), "Handle cannot be null!");
        displayName = handle;
        userCreationDate = LocalDate.now(ZoneId.of("Asia/Almaty"));
        listenerProfile = new ListenerProfile(this);
    }
    public User(String handle, String displayName) {
        this(handle);
        this.displayName = displayName;
    }

    // Getters
    public String getHandle() {
        return handle;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUserCreationDate() {
        return userCreationDate.format(formattedDate);
    }

    public ListenerProfile getListenerProfile() {
        return listenerProfile;
    }

    public ArtistProfile getArtistProfile() {
        return artistProfile;
    }

    public HostProfile getHostProfile() {
        return hostProfile;
    }

    // Setters
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    // Methods
    public void promoteToArtist() {
        artistProfile = new ArtistProfile(this);
    }

    public void promoteToHost() {
        hostProfile = new HostProfile(this);
    }

    public boolean isArtist() {
        return artistProfile != null;
    }

    public boolean isHost() {
        return hostProfile != null;
    }


    // Override methods
    @Override
    public String toString() { // output User{handle, displayName, userCreationDate, isArtist, isHost}
        return "User{handle='" + handle
                + "', displayName='" + displayName
                + "', userCreationDate='" + userCreationDate.format(formattedDate)
                + "', isArtist=" + isArtist()
                + ", isHost=" + isHost()
                + "}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        return handle.equals(user.handle);
    }
    @Override
    public int hashCode() {
        return handle.hashCode();
    }
}