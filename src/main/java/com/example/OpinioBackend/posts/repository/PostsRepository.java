package com.example.OpinioBackend.posts.repository;

import com.example.OpinioBackend.posts.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<PostModel,Long> {
}
