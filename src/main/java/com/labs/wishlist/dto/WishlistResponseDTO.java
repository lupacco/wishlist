package com.labs.wishlist.dto;

import com.labs.wishlist.entities.Wishlist;

import java.util.List;

public record WishlistResponseDTO(
        String clientId,
        List<String> products
) {
    public WishlistResponseDTO(Wishlist wishlist){
        this(wishlist.getClientId(), wishlist.getProductsIds());
    }
}
