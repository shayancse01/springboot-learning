package com.codingshuttle.jpa.Tutorial.jpaTuts.repositories;


import com.codingshuttle.jpa.Tutorial.jpaTuts.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    //List<ProductEntity> findByQuantityAndPrice(Integer quantity, BigDecimal price);

    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan(int quantity, BigDecimal price);
    //This is how you can club more query

    List<ProductEntity> findByTitleContaining(String title);

    //Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    //To create our own custom query
    //@Query("select e from ProductEntity e where e.title=?1 and e.price=?2") //This is our JPQL, and the "title" will be converted to title_x by hibernate
    @Query("select e.title from ProductEntity e where e.title=:title and e.price=:price") //Or we can write our JPQL like this
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

}
