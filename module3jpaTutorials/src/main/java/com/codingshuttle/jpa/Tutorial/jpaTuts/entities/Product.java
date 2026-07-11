package com.codingshuttle.jpa.Tutorial.jpaTuts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "product_table",
        uniqueConstraints = {
                // @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
                @UniqueConstraint(name = "title_price_unique", columnNames = {"tittle_x", "price"})
                //now price and tittle combined is unique
        },
        indexes = {  //An index is a special data structure that helps the database find rows much faster,
                // just like the index at the back of a book.
                @Index(name = "sku_index", columnList = "sku")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20) //Now Hibernate will ensure that this field "sku" is not null, the sql query get generated to create this entity makes ths field as not nullable
    private String sku;  //sku means stock keeping unit
    //I can not add null for this field only inside database
    //and also the length cannot be more than 20

    @Column(name = "tittle_x") //this annotation is used to name the table inside the database
    private String tittle;

    private BigDecimal price;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
