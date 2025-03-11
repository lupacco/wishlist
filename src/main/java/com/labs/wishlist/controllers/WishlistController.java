package com.labs.wishlist.controllers;

import com.labs.wishlist.entities.Wishlist;
import com.labs.wishlist.services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/wishlist")
public class WishlistController {

    public final WishlistService wishlistService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World!");
    }

    @PostMapping("/{clientId}/add/{productId}")
    public ResponseEntity<Wishlist> addProduct(@PathVariable String clientId, @PathVariable String productId){
        Wishlist response = wishlistService.addProductToWishlist(clientId, productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{clientId}/remove/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable String clientId, @PathVariable String productId){
        wishlistService.removeProductFromWishlist(clientId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable String clientId){
        Wishlist response = wishlistService.getWishlist(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{clientId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable String clientId, @PathVariable String productId) {
        Boolean response = wishlistService.isProductInWishlist(clientId, productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
