package com.rent.repository;

import com.rent.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    Iterable<Rent> findByRenter(long ownerId);

    @Query(value = "select i.id, i.title, i.price, i.image_path, r.start_date, r.end_date from item i, rent r where " +
            "i.id = r.item and i.owner=:usrId", nativeQuery = true)
    List<String> findRentItemOwner(@Param("usrId") long usrId);

    @Query(value = "select i.id, i.title, i.price, i.image_path, r.start_date, r.end_date from item i, rent r where " +
            "i.id = r.item and r.renter=:usrId", nativeQuery = true)
    List<String> findRentItemRenter(@Param("usrId") long usrId);

    List<Rent> findByItemAndStartDateBetween(long itemId, LocalDate date1, LocalDate date2);

    List<Rent> findByItemAndEndDateBetween(long itemId, LocalDate date1, LocalDate date2);




}
