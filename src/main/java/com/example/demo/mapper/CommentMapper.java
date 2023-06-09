package com.example.demo.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentUpdateDto;
import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;

public class CommentMapper {

    public static Comment toComment(CommentDto commentDto, User user, Post post ) {
        Comment entity=new Comment();
        entity.setId(commentDto.getId());
        entity.setText(commentDto.getText());
        entity.setUser(user);
        entity.setPost(post);
        return entity;
    }

    public static Comment upComment(Comment entity,CommentUpdateDto commentUpdateDto){
        entity.setText(commentUpdateDto.getText());
        return entity;
    }

}
