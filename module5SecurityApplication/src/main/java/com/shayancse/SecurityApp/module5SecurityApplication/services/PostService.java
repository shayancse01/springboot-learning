package com.shayancse.SecurityApp.module5SecurityApplication.services;

import com.shayancse.SecurityApp.module5SecurityApplication.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
    PostDTO createNewPost(PostDTO inputPost);
    PostDTO getPostById(Long postId);
}
