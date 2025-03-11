package com.labs.wishlist.controllers;

import com.labs.wishlist.dto.WishlistResponseDTO;
import com.labs.wishlist.entities.Wishlist;
import com.labs.wishlist.services.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/wishlist")
public class WishlistController {

    public final WishlistService wishlistService;

    @PostMapping("/{clientId}/add/{productId}")
    public ResponseEntity<WishlistResponseDTO> addProduct(@PathVariable String clientId, @PathVariable String productId){
        log.info("WishlistController.addProduct - start - clientId: {} and productId: {}", clientId, productId);

        WishlistResponseDTO response = wishlistService.addProductToWishlist(clientId, productId);

        log.info("WishlistController.addProduct - end - clientId: {} and productId: {}", clientId, productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{clientId}/remove/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable String clientId, @PathVariable String productId){
        log.info("WishlistController.removeProduct - start - clientId: {} and productId: {}", clientId, productId);

        wishlistService.removeProductFromWishlist(clientId, productId);

        log.info("WishlistController.removeProduct - end - clientId: {} and productId: {}", clientId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<WishlistResponseDTO> getWishlist(@PathVariable String clientId){
        log.info("WishlistController.getWishlist - start - clientId: {}", clientId);

        WishlistResponseDTO response = wishlistService.getWishlist(clientId);

        log.info("WishlistController.getWishlist - end - clientId: {}", clientId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{clientId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable String clientId, @PathVariable String productId) {
        log.info("WishlistController.isProductInWishlist - start - clientId: {} and productId: {}", clientId, productId);

        Boolean response = wishlistService.isProductInWishlist(clientId, productId);

        log.info("WishlistController.isProductInWishlist - end - clientId: {} and productId: {}", clientId, productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
