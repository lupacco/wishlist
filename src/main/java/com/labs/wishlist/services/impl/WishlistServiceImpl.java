package com.labs.wishlist.services.impl;

import com.labs.wishlist.constants.ErrorCodes;
import com.labs.wishlist.dto.WishlistResponseDTO;
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
    public WishlistResponseDTO addProductToWishlist(String clientId, String productId) {
        log.info("WishlistServiceImpl.addProductToWishlist - start - clientId: {} and productId: {}", clientId, productId);
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

        log.info("WishlistServiceImpl.addProductToWishlist - end - clientId: {} and productId: {}", clientId, productId);
        return new WishlistResponseDTO(wishlist);
    }

    @Override
    public void removeProductFromWishlist(String clientId, String productId) {
        log.info("WishlistServiceImpl.removeProductFromWishlist - start - clientId: {} and productId: {}", clientId, productId);
        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElseThrow(() -> new WishlistNotFoundException(ErrorCodes.WISHLIST_NOT_FOUND.getMessage()));

        wishlist.removeProduct(productId);

        wishlistRepository.save(wishlist);
        log.info("WishlistServiceImpl.removeProductFromWishlist - end - clientId: {} and productId: {}", clientId, productId);
    }

    @Override
    public WishlistResponseDTO getWishlist(String clientId) {
        log.info("WishlistController.getWishlist - start - clientId: {}", clientId);
        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElseThrow(() -> new WishlistNotFoundException(ErrorCodes.WISHLIST_NOT_FOUND.getMessage()));
        log.info("WishlistServiceImpl.getWishlist - end - clientId: {}", clientId);
        return new WishlistResponseDTO(wishlist);
    }

    @Override
    public boolean isProductInWishlist(String clientId, String productId) {
        log.info("WishlistServiceImpl.isProductInWishlist - start - clientId: {} and productId: {}", clientId, productId);
        Wishlist wishlist = wishlistRepository.findByClientId(clientId)
                .orElse(null);

        if(wishlist == null) throw new WishlistNotFoundException(ErrorCodes.WISHLIST_NOT_FOUND.getMessage());

        log.info("WishlistServiceImpl.isProductInWishlist - end - clientId: {} and productId: {}", clientId, productId);
        return wishlist.getProductsIds().contains(productId);
    }
}
