package com.example.demo.contoroller;

import com.example.demo.dto.LikeDto;
import com.example.demo.entitiy.Like;
import com.example.demo.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @GetMapping("/findAll")
    public List<Like> findAll(@RequestParam Integer likeId){

        return likeService.findAll(likeId);

    }


    @GetMapping("/find/{id}")
    public Like find(@PathVariable int id){
        return likeService.find(id);
    }

    @PostMapping("/save")
    public LikeDto save(@RequestBody LikeDto likeDto){
        return likeService.save(likeDto);
    }

    @GetMapping("/findnumberoflike/{id}")
    public Integer getNumberOfLike(@PathVariable int id){
        return likeService.getLikeCountByPostId(id);
    }


}
