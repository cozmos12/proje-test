package com.example.demo.service;

import com.example.demo.dto.LikeDto;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.entitiy.Like;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, UserService userService, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public List<Like > findAll(Integer id) {
        if(id!=null) {
            return likeRepository.findByUserId(id);
        }
        return likeRepository.findAll();
    }

    public List<LikeDto> findAllDtoId(Integer id){
        List<LikeDto> likeDtos=new ArrayList<LikeDto>();

        if(id!=null){
            List<Like> likes=likeRepository.findByPostId(id);
            for(Like like:likes){
               LikeDto likeDto= LikeMapper.toDto(like,like.getUser(),like.getPost());
               likeDtos.add(likeDto);

            }
        }
        return likeDtos;


    }
    public List<LikeDto> findAllDto(){
        List<LikeDto> likeDtos=new ArrayList<LikeDto>();


            List<Like> likes=likeRepository.findAll();
            for(Like like:likes){
                LikeDto likeDto=LikeMapper.toDto(like,like.getUser(),like.getPost());
                likeDtos.add(likeDto);

            }
            return likeDtos;


    }

    public int getLikeCountByPostId(int postId) {
        return likeRepository.countLikesByPostId(postId);
    }


    public Like find(int id){
        Optional<Like> comment = likeRepository.findById(id);
        if(comment.isPresent()) {
            Like like=comment.get();
            return like;
        }else{
            return null;
        }
    }

    public LikeDto save(LikeDto likeDto){
        User user=userService.find(Math.toIntExact(likeDto.getUserId()));
        Optional<Post> post =postRepository.findById(likeDto.getPostId());
        if(user!=null&&post!=null){
            Like like= LikeMapper.toLike(likeDto,user,post.get());
            likeRepository.save(like);
            return likeDto;
        }else{
            return null;
        }

    }




}
