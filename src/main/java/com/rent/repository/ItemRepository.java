package com.rent.repository;

import com.rent.models.Item;
import com.rent.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Iterable<Item> findByTitle(String title);
    Iterable<Item> findByOwner(long owner);

}
