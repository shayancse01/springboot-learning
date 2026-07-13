package com.codingshuttle.jpa.Tutorial.jpaTuts;

import com.codingshuttle.jpa.Tutorial.jpaTuts.entities.ProductEntity;
import com.codingshuttle.jpa.Tutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutsApplicationTests {

    @Test
    void contextLoads() {
    }

    //First we hava to inject the ProductRepository here
    @Autowired
    ProductRepository productRepository;

    @Test
    void testRepository() {
        ProductEntity productEntity = ProductEntity.builder()
                .sku("nestle123")
                .title("nestle chocolate")
                .price(BigDecimal.valueOf(123.45))
                .quantity(12)
                .build();

        ProductEntity savedProductEntity = productRepository.save(productEntity);
        System.out.println(savedProductEntity);
    }

    @Test
    void getRepository() {
        //List<ProductEntity> entities = productRepository.findByTitle("Pepsi");
        //List<ProductEntity> entities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1,0,0,0));
        //List<ProductEntity> entities = productRepository.findByQuantityAndPrice(4, BigDecimal.valueOf(23.45));
        //List<ProductEntity> entities = productRepository.findByQuantityGreaterThanAndPriceLessThan(4, BigDecimal.valueOf(23.45));

        List<ProductEntity> entities = productRepository.findByTitleContaining("choco"); //Like(then use %...% ) or Containing
        System.out.println(entities);

    }

    @Test
    void getSingleFromRepository() {
        //As Our return type is One, so we are going with optional instead of a List
        Optional<ProductEntity> productEntity = productRepository.findByTitleAndPrice("Pepsi", BigDecimal.valueOf(14.4));
        productEntity.ifPresent(System.out::println);
    }

}
