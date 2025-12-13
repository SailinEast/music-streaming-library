package library;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class User {
    private final String handle;
    private String displayName;
    private final LocalDate userCreationDate; // LocalDate class that stores date
    private final DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // to format

    // Constructors
    public User(String handle) {
        this.handle = handle;
        displayName = handle;
        userCreationDate = LocalDate.now(ZoneId.of("Asia/Almaty"));
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

    // Setters
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    // Methods


    // Override methods
    @Override
    public String toString() { // output User{handle, displayName, userCreationDate}
        return "User{handle=" + handle
                + ", displayName=" + displayName
                + ", userCreationDate=" + userCreationDate.format(formattedDate)
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