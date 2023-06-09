package com.example.demo.repository;

import com.example.demo.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post>findByUserId(int userId);
}
