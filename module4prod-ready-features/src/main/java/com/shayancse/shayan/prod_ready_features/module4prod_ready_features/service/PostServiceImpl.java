package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.service;

import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.dto.PostDTO;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.entities.PostEntity;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.exceptions.ResourceNotFoundException;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {

        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {

        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);

        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with this Id: " + postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }
}
