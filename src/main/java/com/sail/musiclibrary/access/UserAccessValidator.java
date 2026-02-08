package com.sail.musiclibrary.access;

import com.sail.musiclibrary.common.exception.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class UserAccessValidator {
    public void validate(Long userId, Long requesterId) {
        if (!userId.equals(requesterId)) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
