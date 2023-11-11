package com.example.OpinioBackend.posts.repository;

import com.example.OpinioBackend.posts.models.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<PostModel,Long> {
    Page<PostModel> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
