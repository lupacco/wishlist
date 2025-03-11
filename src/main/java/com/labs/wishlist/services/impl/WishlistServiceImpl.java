package com.labs.wishlist.services.impl;

import com.labs.wishlist.entities.Wishlist;
import com.labs.wishlist.exceptions.WishlistMaxLimitException;
import com.labs.wishlist.exceptions.WishlistNotFoundException;
import com.labs.wishlist.repositories.WishlistRepository;
import com.labs.wishlist.services.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist addProductToWishlist(String clientId, String productId) {

        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElse(new Wishlist());

        log.info("tamo aqui {}", wishlist);
        wishlist.setClientId(clientId);


        if (wishlist.getProductsIds().size() >= 20) {
            throw new WishlistMaxLimitException("A Wishlist atingiu o limite de 20 produtos.");
        }

        if (!wishlist.getProductsIds().contains(productId)) {
            wishlist.getProductsIds().add(productId);
            wishlistRepository.save(wishlist);
        }

        return wishlist;
    }

    @Override
    public void removeProductFromWishlist(String clientId, String productId) {
        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found.")); //todo trocar por padrao de mensagem

        wishlist.getProductsIds().remove(productId);
        wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlist(String clientId) {
        return wishlistRepository.findByClientId(clientId)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found."));
    }

    @Override
    public boolean isProductInWishlist(String clientId, String productId) {
        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElse(null);

        return wishlist != null && wishlist.getProductsIds().contains(productId);
    }
}
