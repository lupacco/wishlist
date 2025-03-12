package com.labs.wishlist.handler;

import com.labs.wishlist.constants.ErrorCodes;
import com.labs.wishlist.exceptions.ExceptionResponse;
import com.labs.wishlist.exceptions.ProductNotFoundException;
import com.labs.wishlist.exceptions.WishlistMaxLimitException;
import com.labs.wishlist.exceptions.WishlistNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerGenericException(HttpServletRequest request, Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .code(ErrorCodes.INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorCodes.INTERNAL_SERVER_ERROR.getMessage())
                .instance(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(WishlistNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleWishListNotFoundException(HttpServletRequest request, WishlistNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse.builder()
                .code(ErrorCodes.WISHLIST_NOT_FOUND.getCode())
                .message(ErrorCodes.WISHLIST_NOT_FOUND.getMessage())
                .instance(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(WishlistMaxLimitException.class)
    public ResponseEntity<ExceptionResponse> handleWishlistMaxLimitException(HttpServletRequest request, WishlistMaxLimitException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionResponse.builder()
                .code(ErrorCodes.WISHLIST_MAX_LIMIT.getCode())
                .message(ErrorCodes.WISHLIST_MAX_LIMIT.getMessage())
                .instance(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProductNotFoundException(HttpServletRequest request, ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse.builder()
                .code(ErrorCodes.PRODUCT_NOT_FOUND.getCode())
                .message(ErrorCodes.PRODUCT_NOT_FOUND.getMessage())
                .instance(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoResourceFoundException(HttpServletRequest request, NoResourceFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.builder()
                .code(ErrorCodes.BAD_REQUEST.getCode())
                .message(ErrorCodes.BAD_REQUEST.getMessage())
                .instance(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
