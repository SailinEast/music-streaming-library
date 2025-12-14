package library.manager;

import library.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void registerUser(User newUser) {
        boolean handleExists = users.stream().anyMatch(u -> u.getHandle()
                .equalsIgnoreCase(newUser.getHandle()));
        if (handleExists) {
            throw new IllegalArgumentException("User handle " + newUser.getHandle() + " already exists!");
        }
        users.add(newUser);
    }

    public void rmUser(User user) {
        users.remove(user);
    }

    public List<User> findUsersByDisplayName(String displayName) {
        return users.stream().filter(u ->
                u.getDisplayName().trim().equalsIgnoreCase(displayName.trim())).toList();
    }

    public List<User> sortUserHandlesAlphabetical() {
        return users.stream().sorted(Comparator.comparing(User::getHandle, String.CASE_INSENSITIVE_ORDER)).toList();
    }
}
