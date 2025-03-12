package com.labs.wishlist.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "wishlists")
public class Wishlist {

    @Id
    private String id;
    private String clientId;
    private List<String> productsIds = new ArrayList<>();
}
