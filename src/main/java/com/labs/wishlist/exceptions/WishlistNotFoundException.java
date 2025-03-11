package com.labs.wishlist.exceptions;

import java.io.Serial;

public class WishlistNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public WishlistNotFoundException(String message) { super(message); }
}
