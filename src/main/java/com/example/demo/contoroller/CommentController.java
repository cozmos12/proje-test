package com.example.demo.contoroller;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.CommentUpdateDto;
import com.example.demo.entitiy.Comment;
import com.example.demo.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")

public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/findAll")
    public List<Comment> findAll(@RequestParam Optional<Integer> userId){

        return commentService.findAll(userId);

    }

    @GetMapping("/find/{id}")
    public Comment find(@PathVariable int id){
        return commentService.find(id);
    }



    @PostMapping("/save")
    public CommentDto save(@RequestBody CommentDto commentDto){
         return commentService.save(commentDto);
    }

    @PutMapping("/update/{id}")
    public CommentUpdateDto update(@RequestBody CommentUpdateDto commentUpdateDto,@PathVariable int id){
        return commentService.update(id,commentUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        commentService.delete(id);

    }
    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        commentService.deleteAll();
    }

}
