package com.example.demo.repository;

import com.example.demo.entitiy.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Integer> {
    List<Like> findByUserIdAndPostId(int userId, int postId);

    List<Like> findByUserId(int userId);

    List<Like> findByPostId(int id);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.id = :postId")
    int countLikesByPostId(@Param("postId") int postId);


}
