package com.labs.wishlist.repositories;

import com.labs.wishlist.entities.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    Optional<Wishlist> findByClientId(String clientId);
}
