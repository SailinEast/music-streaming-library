package com.sail.musiclibrary.user;

import com.sail.musiclibrary.common.dto.user.UserRequest;
import com.sail.musiclibrary.common.dto.user.UserResponse;
import com.sail.musiclibrary.playlist.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Get user
    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        User user = userService.findById(userId);

        return new UserResponse(
            user.getId(),
            user.getHandle(),
            user.isArtist(),
            user.getPlaylists().stream().map(Playlist::getName).toList()
        );
    }

    // Create user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest request) {
        User newUser = userService.createUser(request.handle());

        return new UserResponse(
            newUser.getId(),
            newUser.getHandle(),
            newUser.isArtist(),
            Collections.emptyList()
        );
    }

    // TODO: User handle and displayName
    //       to PUT rename displayName

    // Delete user
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId, @RequestParam Long requesterId) {
        userService.deleteUser(userId, requesterId);
    }
}
