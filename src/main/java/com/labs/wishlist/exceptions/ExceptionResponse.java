package com.labs.wishlist.exceptions;

import com.labs.wishlist.constants.ErrorCodes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ExceptionResponse {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String code;
    private final String message;
    private final String instance;
    private final LocalDateTime timestamp;

    public ExceptionResponse(ErrorCodes errorCode, String instance) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.instance = instance;
        this.timestamp = LocalDateTime.now();
    }
}
