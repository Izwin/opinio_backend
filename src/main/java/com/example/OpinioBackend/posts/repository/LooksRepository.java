package com.example.OpinioBackend.posts.repository;

import com.example.OpinioBackend.posts.models.LookModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LooksRepository extends JpaRepository<LookModel,Long> {
    Page<LookModel> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
