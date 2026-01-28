package com.sail.musiclibrary.user;

import com.sail.musiclibrary.common.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, Long> {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(String handle) {
        User user = new User(handle);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userId, Long requesterId) {
        if (!userId.equals(requesterId)) throw new SecurityException();

        userRepository.deleteById(userId);
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }
}
