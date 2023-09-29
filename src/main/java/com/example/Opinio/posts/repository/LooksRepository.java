package com.example.Opinio.posts.repository;

import com.example.Opinio.posts.models.LookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LooksRepository extends JpaRepository<LookModel,Long> {
}
