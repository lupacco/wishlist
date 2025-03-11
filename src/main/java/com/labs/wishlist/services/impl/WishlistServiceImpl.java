package com.labs.wishlist.services.impl;

import com.labs.wishlist.constants.ErrorCodes;
import com.labs.wishlist.entities.Wishlist;
import com.labs.wishlist.exceptions.WishlistNotFoundException;
import com.labs.wishlist.repositories.WishlistRepository;
import com.labs.wishlist.services.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist addProductToWishlist(String clientId, String productId) {

        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElse(Wishlist.builder()
                        .clientId(clientId)
                        .productsIds(new ArrayList<>())
                        .build());


        wishlist.checkWishlistLimit();

        if (!wishlist.containsProduct(productId)) {
            wishlist.addProduct(productId);
            wishlistRepository.save(wishlist);
        }

        return wishlist;
    }

    @Override
    public void removeProductFromWishlist(String clientId, String productId) {
        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElseThrow(() -> new WishlistNotFoundException(ErrorCodes.WISHLIST_NOT_FOUND.getMessage()));

        wishlist.removeProduct(productId);

        wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlist(String clientId) {
        return wishlistRepository.findByClientId(clientId)
                .orElseThrow(() -> new WishlistNotFoundException(ErrorCodes.WISHLIST_NOT_FOUND.getMessage()));
    }

    @Override
    public boolean isProductInWishlist(String clientId, String productId) {
        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElse(null);

        if(wishlist == null) throw new WishlistNotFoundException(ErrorCodes.WISHLIST_NOT_FOUND.getMessage());

        return wishlist.getProductsIds().contains(productId);
    }
}
