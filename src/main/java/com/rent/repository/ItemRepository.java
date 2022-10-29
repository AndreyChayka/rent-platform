package com.rent.repository;

import com.rent.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Iterable<Item> findByTitle(String title);
    Iterable<Item> findByOwner(long owner);

    List<Item> findByTitleContaining(String title);

    List<Item> findByAboutContaining(String about);

}
