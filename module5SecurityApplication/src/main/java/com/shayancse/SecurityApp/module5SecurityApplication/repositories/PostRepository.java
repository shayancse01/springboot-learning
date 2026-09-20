package com.shayancse.SecurityApp.module5SecurityApplication.repositories;

import com.shayancse.SecurityApp.module5SecurityApplication.entities.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}