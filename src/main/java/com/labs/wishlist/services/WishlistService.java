package com.labs.wishlist.services;

import com.labs.wishlist.entities.Wishlist;

public interface WishlistService {

    Wishlist addProductToWishlist(String clientId, String productId);

    void removeProductFromWishlist(String clientId, String productId);

    Wishlist getWishlist(String clientId);

    boolean isProductInWishlist(String clientId, String productId);
}
