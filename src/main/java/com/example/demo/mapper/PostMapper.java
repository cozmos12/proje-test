package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;

import java.util.List;

public class PostMapper {

    public static Post toPost(PostSaveDto postDto, User user) {
        Post entity=new Post();
        entity.setText(postDto.getText());
        entity.setTitle(postDto.getTitle());
        entity.setUser(user);
       return entity;
    }

    public static PostDtoLikeNumber toDtoLikeNumber(Post post, User user, int Like) {
        PostDtoLikeNumber entity=new PostDtoLikeNumber();
        entity.setId(post.getId());
        entity.setText(post.getText());
        entity.setTitle(post.getTitle());
        entity.setUserName(user.getUserName());
        entity.setUserId(user.getId());
        entity.setLikeOfNumber(Like);

        return entity;
    }

    public static PostDtoLike toDtoLike(Post post, User user, List<LikeDto> likes) {
        PostDtoLike entity=new PostDtoLike();
        entity.setId(post.getId());
        entity.setText(post.getText());
        entity.setTitle(post.getTitle());
        entity.setUserName(user.getUserName());
        entity.setUserId(user.getId());
        entity.setLikes(likes);
        return entity;
    }

    public static PostDto toDto(Post post, User user) {
        PostDto entity=new PostDto();
        entity.setId(post.getId());
        entity.setText(post.getText());
        entity.setTitle(post.getTitle());
        entity.setUserName(user.getUserName());
        entity.setUserId(user.getId());

        return entity;
    }

    public static Post postUp(Post post,PostUpdateDto postUpdateDto){
        post.setText(postUpdateDto.getText());
        post.setTitle(postUpdateDto.getTitle());
        return post;

    }




}
