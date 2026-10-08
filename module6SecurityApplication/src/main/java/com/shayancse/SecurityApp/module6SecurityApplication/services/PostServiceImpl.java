package com.shayancse.SecurityApp.module6SecurityApplication.services;

import com.shayancse.SecurityApp.module6SecurityApplication.dto.PostDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.PostEntity;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module6SecurityApplication.exceptions.ResourceNotFoundException;
import com.shayancse.SecurityApp.module6SecurityApplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

        //We can do our authentication here as well
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //getPrinciple will contain the user details
        log.info("user {}", user); //for printing the user log in the console

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