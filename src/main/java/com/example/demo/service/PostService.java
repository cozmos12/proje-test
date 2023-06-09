package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;


    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<Post> getAllPost(Optional<Integer> userId){
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public List<PostDto> getAllToDto(Optional<Integer> userId){
        List<PostDto>postDtos = new ArrayList<PostDto>();

        if(userId.isPresent()){
            List<Post> result=postRepository.findByUserId(userId.get());

            for(Post post : result){
                User user=post.getUser();
                postDtos.add(PostMapper.toDto(post,user));
            }
            return postDtos;
        }
        List<Post>posts=postRepository.findAll();


        for(Post post : posts){
            User user=post.getUser();
            postDtos.add(PostMapper.toDto(post,user));
        }
        return postDtos;
    }

    public List<PostDtoLike> getAllToDtoLike(Optional<Integer> userId,Optional<Integer>postId){
        List<PostDtoLike>postDtos = new ArrayList<PostDtoLike>();

        if(userId.isPresent()&&postId.isPresent()){
            List<Post> result=postRepository.findByUserId(userId.get());
            List<LikeDto>likeDtos= likeService.findAllDtoId(postId.get());

            for(Post post : result){
                User user=post.getUser();



                postDtos.add(PostMapper.toDtoLike(post,user,likeDtos));
            }
            return postDtos;
        }
        List<Post>posts=postRepository.findAll();
        List<LikeDto>likeDtos=likeService.findAllDto();


        for(Post post : posts){
            User user=post.getUser();
            postDtos.add(PostMapper.toDtoLike(post,user,likeDtos));
        }
        return postDtos;
    }

    public Post find(int id) {
        Optional<Post> result = postRepository.findById(id);
        Post post = result.get();
        User user=post.getUser();

        if(result.isPresent()) {
            post = result.get();
        }else{
            throw new RuntimeException("Customer not found :"+id);
        }
        return post;
    }


    public PostDtoLikeNumber findToDtoLikeNumber(int id) {
        Optional<Post> result = postRepository.findById(id);
        Post post = result.get();
        User user=post.getUser();
        int Like=likeService.getLikeCountByPostId(id);
        if(result.isPresent()) {
            PostDtoLikeNumber postDto= PostMapper.toDtoLikeNumber(post,user,Like);
            return postDto;
        }else{
            throw new RuntimeException("Customer not found :"+id);
        }
    }

    public PostDtoLike findToDtoLike(int id) {
        Optional<Post> result = postRepository.findById(id);
        List<LikeDto> likeDtos=likeService.findAllDtoId(id);
        Post post = result.get();
        User user=post.getUser();
        if(result.isPresent()) {
            PostDtoLike postDto= PostMapper.toDtoLike(post,user,likeDtos);
            return postDto;
        }else{
            throw new RuntimeException("Customer not found :"+id);
        }
    }

    public PostSaveDto save(PostSaveDto postDto){
        User user= userService.find(postDto.getUserId());
        if(user==null){
            return null;
        }else{
            Post post= PostMapper.toPost(postDto,user);
            postRepository.save(post);
            return postDto;
        }
    }

    public Post update(int id,PostUpdateDto postUpdateDto){
        Optional<Post> post=postRepository.findById(id);
        if(post.isPresent()){

            Post post1=post.get();
            Post posts=PostMapper.postUp(post1,postUpdateDto);
            postRepository.save(posts);
            return posts;
        }else{
            return null;
        }
    }

    public void delete(int id){
        postRepository.deleteById(id);
    }

    public void deleteAll(){
        postRepository.deleteAll();
    }



}


