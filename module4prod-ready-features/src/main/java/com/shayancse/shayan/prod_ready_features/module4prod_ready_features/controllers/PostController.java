package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.controllers;

import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.dto.PostDTO;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

    //finding post by it's ID
    @GetMapping(path = "/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }
}
