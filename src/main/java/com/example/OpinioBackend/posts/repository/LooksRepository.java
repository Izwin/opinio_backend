package com.example.OpinioBackend.posts.repository;

import com.example.OpinioBackend.posts.models.LookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LooksRepository extends JpaRepository<LookModel,Long> {
}
