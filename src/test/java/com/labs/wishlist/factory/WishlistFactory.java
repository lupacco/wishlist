package com.labs.wishlist.factory;

import com.labs.wishlist.dto.WishlistResponseDTO;
import com.labs.wishlist.entities.Wishlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WishlistFactory {

    public static WishlistResponseDTO createValidWishlistResponseDTO(String clientId, String productId){
        List<String> products = new ArrayList<>();
        products.add(productId);
        return new WishlistResponseDTO(clientId, products);
    }

    public static Wishlist createValidWishlist(String clientId, String productId){
        List<String> products = new ArrayList<>();
        products.add(productId);
        return Wishlist.builder()
                .id("anyId")
                .clientId(clientId)
                .productsIds(products)
                .build();
    }

    public static Wishlist createValidWishlistWithOneProduct(String clientId){
        List<String> products = new ArrayList<>();
        products.add("999");
        return Wishlist.builder()
                .id("anyId")
                .clientId(clientId)
                .productsIds(products)
                .build();
    }

    public static Wishlist createValidWishListWithFullProducts(String clientId) {
        List<String> products = IntStream.rangeClosed(1, 20)
                .mapToObj(String::valueOf)
                .toList();

        return Wishlist.builder()
                .id("anyId")
                .clientId(clientId)
                .productsIds(products)
                .build();

    }

    public static Wishlist createValidWishlistWithEmptyProduct(String clientId){
        List<String> products = new ArrayList<>();
        return Wishlist.builder()
                .id("anyId")
                .clientId(clientId)
                .productsIds(new ArrayList<>())
                .build();
    }

}
