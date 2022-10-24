package com.rent.repository;

import com.rent.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    Iterable<Rent> findByRenter(long ownerId);

}
