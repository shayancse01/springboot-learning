package com.shayancse.SecurityApp.module5SecurityApplication.services;

import com.shayancse.SecurityApp.module5SecurityApplication.dto.PostDTO;
import com.shayancse.SecurityApp.module5SecurityApplication.entities.PostEntity;
import com.shayancse.SecurityApp.module5SecurityApplication.exceptions.ResourceNotFoundException;
import com.shayancse.SecurityApp.module5SecurityApplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
        PostEntity postEntity =
                modelMapper.map(inputPost, PostEntity.class);

        return modelMapper.map(
                postRepository.save(postEntity),
                PostDTO.class
        );
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Post not found with id " + postId
                        )
                );

        return modelMapper.map(postEntity, PostDTO.class);
    }
}