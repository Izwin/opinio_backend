package com.example.Opinio.posts.repository;

import com.example.Opinio.posts.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<PostModel,Long> {
}
