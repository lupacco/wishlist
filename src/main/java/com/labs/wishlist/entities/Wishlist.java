package com.labs.wishlist.entities;

import com.labs.wishlist.constants.ErrorCodes;
import com.labs.wishlist.exceptions.ProductNotFoundException;
import com.labs.wishlist.exceptions.WishlistMaxLimitException;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "wishlists")
public class Wishlist {

    @Id
    private String id;
    private String clientId;
    private List<String> productsIds;

    public void checkWishlistLimit(){
        if(this.productsIds.size() >= 20) throw new WishlistMaxLimitException(ErrorCodes.WISHLIST_MAX_LIMIT.getMessage());
    }

    public boolean containsProduct(String productId){
        return this.productsIds.contains(productId);
    }

    public void addProduct(String productId){
        this.productsIds.add(productId);
    }

    public void removeProduct(String productId){
        if(!containsProduct(productId)) throw new ProductNotFoundException(ErrorCodes.PRODUCT_NOT_FOUND.getMessage());
        this.productsIds.remove(productId);
    }
}
