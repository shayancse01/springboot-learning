package com.codingshuttle.jpa.Tutorial.jpaTuts.controllers;

import com.codingshuttle.jpa.Tutorial.jpaTuts.entities.ProductEntity;
import com.codingshuttle.jpa.Tutorial.jpaTuts.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 5;

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //we should not inject repo class directly to the controller, we must have a service in between
    //But now for learning purpose am doing it

    @GetMapping
    public List<ProductEntity> getAllProducts(  //use Page instead of List to get all the information
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {

        //return productRepository.findBy(Sort.by(sortBy));

        //return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price","quantity"));
        //If after sorting two products have same thing, then they will be further sort by price then quantity
        //we can also write it like this -->
/*
        return productRepository.findBy(Sort.by(
                Sort.Order.desc(sortBy), //first it will try to sort by descending
                Sort.Order.asc("price") //If two products have same thing after sorting it by descending then it will sort it by price
                //and we can add more fields like this
        ));

*/
        //Sorting is also done inside page
/*
        Pageable pageable = PageRequest.of(
                pageNumber,
                PAGE_SIZE,
                Sort.by(sortBy));  //for sorting
        return productRepository.findAll(pageable).getContent();
*/
        return productRepository.findByTitleContainingIgnoreCase(
                title,
                PageRequest.of(
                        pageNumber,
                        PAGE_SIZE,
                        Sort.by(sortBy))
        );

    }

}
