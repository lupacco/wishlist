package com.labs.wishlist.exceptions;

import java.io.Serial;

public class WishlistMaxLimitException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public WishlistMaxLimitException(String message) { super(message); }
}
