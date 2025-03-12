package com.labs.wishlist.services;

import com.labs.wishlist.constants.ErrorCodes;
import com.labs.wishlist.dto.WishlistResponseDTO;
import com.labs.wishlist.exceptions.ProductNotFoundException;
import com.labs.wishlist.exceptions.WishlistMaxLimitException;
import com.labs.wishlist.exceptions.WishlistNotFoundException;
import com.labs.wishlist.factory.WishlistFactory;
import com.labs.wishlist.repositories.WishlistRepository;
import com.labs.wishlist.services.impl.WishlistServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
public class WishlistServicesTest {

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNewWishListAndAddProductToWishlist_WhenSuccessful(){
        String clientId = "777";
        String productId = "123";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                        .thenReturn(Optional.of(WishlistFactory.createValidWishlistWithEmptyProduct(clientId)));


        WishlistResponseDTO result = wishlistService.addProductToWishlist(clientId, productId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.clientId()).isEqualTo(clientId);
        Assertions.assertThat(result.products().get(0)).isEqualTo(productId);
        Assertions.assertThat(result.products()).hasSize(1);

    }

    @Test
    void duplicatedProductsAreNotAppendedToWishlist_WhenSuccessful(){
        String clientId = "777";
        String productId = "999";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                .thenReturn(Optional.of(WishlistFactory.createValidWishlistWithOneProduct(clientId)));

        WishlistResponseDTO result = wishlistService.addProductToWishlist(clientId, productId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.clientId()).isEqualTo(clientId);
        Assertions.assertThat(result.products().getFirst()).isEqualTo(productId);
        Assertions.assertThat(result.products()).hasSize(1);

    }

    @Test
    void createAndAddProductToWishlist_ThrowsWishListMaxLimitException_WhenReachMaxLimit(){
        String clientId = "777";
        String productId = "123";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                        .thenReturn(Optional.of(WishlistFactory.createValidWishListWithFullProducts(clientId)));

        Assertions.assertThatExceptionOfType(WishlistMaxLimitException.class)
                .isThrownBy(() -> this.wishlistService.addProductToWishlist(clientId, productId));
    }

    @Test
    void removeProductFromWishList_WhenSuccessful(){
        String clientId = "777";
        String productId = "999";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                .thenReturn(Optional.of(WishlistFactory.createValidWishlistWithOneProduct(clientId)));
        BDDMockito.when(wishlistRepository.save(any()))
                .thenReturn(WishlistFactory.createValidWishlistWithEmptyProduct(clientId));

        wishlistService.removeProductFromWishlist(clientId, productId);

        verify(wishlistRepository, times(1)).findByClientId(clientId);
        verify(wishlistRepository, times(1)).save(any());

    }

    @Test
    void removeProductFromWishList_ThrowsProductNotFoundException(){
        String clientId = "777";
        String productId = "999";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                .thenReturn(Optional.of(WishlistFactory.createValidWishlistWithEmptyProduct(clientId)));

        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> this.wishlistService.removeProductFromWishlist(clientId, productId))
                .withMessageContaining(ErrorCodes.PRODUCT_NOT_FOUND.getMessage());
        verify(wishlistRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void getWishList_findWishListByClientId_WhenSuccessful(){
        String clientId = "777";
        String productId = "999";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                .thenReturn(Optional.of(WishlistFactory.createValidWishlistWithOneProduct(clientId)));

        WishlistResponseDTO result = wishlistService.getWishlist(clientId);

        verify(wishlistRepository, times(1)).findByClientId(clientId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.clientId()).isEqualTo(clientId);
        Assertions.assertThat(result.products().getFirst()).isEqualTo(productId);
        Assertions.assertThat(result.products()).hasSize(1);

    }

    @Test
    void isProductInWishlist_ReturnsTrue_WhenWishlistContainsProduct(){
        String clientId = "777";
        String productId = "999";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                .thenReturn(Optional.of(WishlistFactory.createValidWishlistWithOneProduct(clientId)));

        boolean result = wishlistService.isProductInWishlist(clientId, productId);

        verify(wishlistRepository, times(1)).findByClientId(clientId);
        Assertions.assertThat(result).isEqualTo(true);

    }

    @Test
    void isProductInWishlist_ThrowsWishListNotFoundException_WhenWishlistDoNotContainsProduct(){
        String clientId = "777";
        String productId = "999";

        BDDMockito.when(wishlistRepository.findByClientId(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(WishlistNotFoundException.class)
                .isThrownBy(() -> this.wishlistService.isProductInWishlist(clientId, productId))
                .withMessageContaining(ErrorCodes.WISHLIST_NOT_FOUND.getMessage());

    }
}
