package com.labs.wishlist.handler;

import com.labs.wishlist.constants.ErrorCodes;
import com.labs.wishlist.exceptions.ProductNotFoundException;
import com.labs.wishlist.exceptions.WishlistMaxLimitException;
import com.labs.wishlist.exceptions.WishlistNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Mock
    private HttpServletRequest request;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handlerGenericException() {
        ResponseEntity<?> response = restExceptionHandler.handlerGenericException(request, new Exception());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void handleWishListNotFoundException() {
        ResponseEntity<?> response = restExceptionHandler.handleWishListNotFoundException(request, new WishlistNotFoundException(ErrorCodes.WISHLIST_NOT_FOUND.getMessage()));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testHandleWishlistMaxLimitException() {
        ResponseEntity<?> response = restExceptionHandler.handleWishlistMaxLimitException(request, new WishlistMaxLimitException(ErrorCodes.WISHLIST_MAX_LIMIT.getMessage()));
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void handleProductNotFoundException() {
        ResponseEntity<?> response = restExceptionHandler.handleProductNotFoundException(request, new ProductNotFoundException(ErrorCodes.PRODUCT_NOT_FOUND.getMessage()));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void handleNoResourceFoundException() {
        ResponseEntity<?> response = restExceptionHandler.handleNoResourceFoundException(request, new NoResourceFoundException(HttpMethod.GET, "/test"));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}