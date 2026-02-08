package com.sail.musiclibrary.common;

import com.sail.musiclibrary.common.dto.error.ErrorResponse;
import com.sail.musiclibrary.common.exception.AccessDeniedException;
import com.sail.musiclibrary.common.exception.NotArtistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({NoResourceFoundException.class, NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException re) {
        log.warn("Resource Not Found: {}", re.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, re.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class, NotArtistException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException re) {
        log.warn("Bad Request: {}", re.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, re.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleSecurity(AccessDeniedException ade) {
        log.warn("Unauthorized Access: {}", ade.getMessage());
        return buildResponse(HttpStatus.FORBIDDEN, ade.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalError(Exception e) {
        log.error("Unexpected Internal Error", e);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected internal error occurred.");
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
        ErrorResponse response = new ErrorResponse(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(response, status);
    }
}
