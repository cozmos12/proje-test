package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentUpdateDto;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;
import com.example.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> findAll(Optional<Integer> id) {
        if(id.isPresent()) {
            return commentRepository.findByUserId(id.get());
        }
        return commentRepository.findAll();
    }

    public Comment find(int id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {
            Comment comment1=comment.get();
            return comment1;
        }else{
            return null;
        }
    }

    public CommentDto save(CommentDto comment){
        User user=userService.find(comment.getUserId());
        Post post=postService.find(comment.getPostId());
        if(user!=null&&post!=null){
            Comment comment1=CommentMapper.toComment(comment,user,post);
            commentRepository.save(comment1);
            return comment;
        }else{
            return null;
        }

    }

    public CommentUpdateDto update(int id,CommentUpdateDto commentUpdateDto){
        Optional<Comment> comment=commentRepository.findById(id);
        if(comment.isPresent()) {
            Comment comment1=comment.get();
            Comment comment2=CommentMapper.upComment(comment1,commentUpdateDto);
            commentRepository.save(comment2);
            return commentUpdateDto;
        }else{
            return null;
        }
    }

    public void delete(int id){
        commentRepository.deleteById(id);
    }
    public void deleteAll(){
        commentRepository.deleteAll();
    }


}
