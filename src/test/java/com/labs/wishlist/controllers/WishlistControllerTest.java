package com.labs.wishlist.controllers;

import com.labs.wishlist.dto.WishlistResponseDTO;
import com.labs.wishlist.factory.WishlistFactory;
import com.labs.wishlist.services.impl.WishlistServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void removeProductFromWishlist_WhenSuccessful(){
        String clientId = "777";
        String productId = "123";

        BDDMockito.doNothing().when(wishlistService).removeProductFromWishlist(any(), any());

        wishlistController.removeProduct(clientId, productId).getBody();

        verify(wishlistService, times(1)).removeProductFromWishlist(clientId, productId);
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

    @Test
    void returnTrueForProductInWishlist_WhenSuccessful(){
        String clientId = "777";
        String productId = "123";

        BDDMockito.when(wishlistService.isProductInWishlist(any(), any()))
                .thenReturn(true);

        boolean response = wishlistController.isProductInWishlist(clientId, productId).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void returnFalseForProductIsNotInWishlist_WhenSuccessful(){
        String clientId = "777";
        String productId = "123";

        BDDMockito.when(wishlistService.isProductInWishlist(any(), any()))
                .thenReturn(false);

        boolean response = wishlistController.isProductInWishlist(clientId, productId).getBody();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(false);
    }
}
