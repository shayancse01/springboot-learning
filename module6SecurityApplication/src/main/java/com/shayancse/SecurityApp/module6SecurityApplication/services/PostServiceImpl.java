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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostEntity postEntity =
                modelMapper.map(inputPost, PostEntity.class);

        postEntity.setAuthor(user);

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