package com.sail.musiclibrary.user;

import com.sail.musiclibrary.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, Long> {
    private final UserRepository userRepository;

    public User createUser(String handle) {
        return userRepository.save(new User(handle));
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }
}
