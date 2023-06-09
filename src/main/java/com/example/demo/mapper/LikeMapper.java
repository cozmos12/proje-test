package com.example.demo.mapper;

import com.example.demo.dto.LikeDto;
import com.example.demo.entitiy.Like;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;

public class LikeMapper {

    public static Like toLike(LikeDto likeDto, User user, Post post){
        Like entity=new Like();
        entity.setId(likeDto.getId());
        entity.setUser(user);
        entity.setPost(post);
        return entity;
    }


    public static LikeDto toDto(Like like,User user,Post post){
      LikeDto entity=new LikeDto();
      entity.setId(like.getId());
      entity.setUserId(user.getId());
      entity.setPostId(post.getId());

      return entity;
    }

}
