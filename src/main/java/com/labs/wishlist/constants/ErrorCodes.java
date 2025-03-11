package com.labs.wishlist.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    INTERNAL_SERVER_ERROR("500", "Internal server error."),
    WISHLIST_NOT_FOUND("404", "Wishlist not found."),
    WISHLIST_MAX_LIMIT("409", "Wishlist reached max limit of products."),
    PRODUCT_NOT_FOUND("404", "Product not found."),
    MISSING_CLIENT_ID("400", "ClientId's wishlist is missing."),
    MISSING_PRODUCT_ID("400", "ProductId is missing.");

    private final String code;
    private final String message;

    ErrorCodes(String code, String message){
        this.code = code;
        this.message = message;
    }


}
