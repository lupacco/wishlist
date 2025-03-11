package com.labs.wishlist.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    INTERNAL_SERVER_ERROR("500", "Internal server error."),
    WISHLIST_NOT_FOUND("404", "Wishlist not found."),
    WISHLIST_MAX_LIMIT("409", "Wishlist reached max limit of products."),
    PRODUCT_NOT_FOUND("404", "Product not found."),
    BAD_REQUEST("400", "ClientId or ProductId are missing.");

    private final String code;
    private final String message;

    ErrorCodes(String code, String message){
        this.code = code;
        this.message = message;
    }


}
