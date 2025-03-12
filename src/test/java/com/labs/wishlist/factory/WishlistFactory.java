package com.labs.wishlist.factory;

import com.labs.wishlist.dto.WishlistResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class WishlistFactory {

    public static WishlistResponseDTO createValidWishlistResponseDTO(String clientId, String productId){
        List<String> products = new ArrayList<>();
        products.add(productId);
        return new WishlistResponseDTO(clientId, products);
    }
}
