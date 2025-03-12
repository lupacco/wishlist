package com.labs.wishlist.services;

import com.labs.wishlist.dto.WishlistContainsProductResponseDTO;
import com.labs.wishlist.dto.WishlistResponseDTO;
import com.labs.wishlist.entities.Wishlist;

public interface WishlistService {

    WishlistResponseDTO addProductToWishlist(String clientId, String productId);

    void removeProductFromWishlist(String clientId, String productId);

    WishlistResponseDTO getWishlist(String clientId);

    WishlistContainsProductResponseDTO isProductInWishlist(String clientId, String productId);
}
