package com.example.demo.contoroller;

import com.example.demo.dto.*;
import com.example.demo.entitiy.Post;
import com.example.demo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/save")
    public PostSaveDto save(@RequestBody PostSaveDto post){
       return postService.save(post);
    }

    @PutMapping("/update/{postId}")
    public Post update(@RequestBody PostUpdateDto update,@PathVariable int  postId){
        return postService.update(postId,update);
    }

    @GetMapping("/findAll")
    public List<Post> findAll(@RequestParam Optional<Integer> userId){
       return postService.getAllPost(userId);
    }

    @GetMapping("/findAllToDto")
    public List<PostDto> findAllToDto(@RequestParam Optional<Integer> userId){
        return postService.getAllToDto(userId);
    }

    @GetMapping("/findAllToDtoLike")
    public List<PostDtoLike> findAllToDtoLike(@RequestParam Optional<Integer> userId, @RequestParam Optional<Integer> postId){
        return postService.getAllToDtoLike(userId,postId);
    }

    @GetMapping("/findToDtoLike/{postId}")
    public PostDtoLike findLike(@PathVariable int postId){
        return postService.findToDtoLike(postId);
    }

    @GetMapping("/find/{postId}")
    public Post find(@PathVariable int postId){
        return postService.find(postId);
    }

    @GetMapping("/findDtoLikeNumber/{postId}")
    public PostDtoLikeNumber findToDto(@PathVariable int postId){
        return postService.findToDtoLikeNumber(postId);
    }

    @DeleteMapping("/delete")
    public void delete(int id){
        postService.delete(id);
    }
    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        postService.deleteAll();
    }
}
