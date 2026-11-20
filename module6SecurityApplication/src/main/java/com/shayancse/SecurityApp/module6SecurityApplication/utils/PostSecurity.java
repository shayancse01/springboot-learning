package com.shayancse.SecurityApp.module6SecurityApplication.utils;

import com.shayancse.SecurityApp.module6SecurityApplication.dto.PostDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module6SecurityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity { //This class is Responsible for securing all of my posts

    private final PostService postService;

    public boolean isOwnerOfPost(Long postID) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //Getting the User
        PostDTO post = postService.getPostById(postID); //Getting the post

        //Checking whether the current user is the owner of that post or not
        return post.getAuthor().getId().equals(user.getId());

    }
}
