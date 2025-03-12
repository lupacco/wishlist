package com.labs.wishlist.controllers;

import com.labs.wishlist.dto.WishlistResponseDTO;
import com.labs.wishlist.factory.WishlistFactory;
import com.labs.wishlist.services.impl.WishlistServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

@Slf4j
public class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;

    @Mock
    private WishlistServiceImpl wishlistService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAndAddProductToWishlist_WhenSuccessful(){
        String clientId = "777";
        String productId = "123";

        BDDMockito.when(wishlistService.addProductToWishlist(any(), any()))
                .thenReturn(WishlistFactory.createValidWishlistResponseDTO(clientId, productId));

        WishlistResponseDTO response = wishlistController.addProduct(clientId, productId).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.clientId()).isEqualTo(clientId);
        Assertions.assertThat(response.products().get(0)).isEqualTo(productId);
        Assertions.assertThat(response.products()).hasSize(1);

    }

    @Test
    void getWishlist_WhenSuccessful(){
        String clientId = "777";
        String productId = "123";

        BDDMockito.when(wishlistService.getWishlist(any()))
                .thenReturn(WishlistFactory.createValidWishlistResponseDTO(clientId, productId));

        WishlistResponseDTO response = wishlistController.getWishlist(clientId).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.clientId()).isEqualTo(clientId);
        Assertions.assertThat(response.products().get(0)).isEqualTo(productId);
        Assertions.assertThat(response.products()).hasSize(1);
    }
}
