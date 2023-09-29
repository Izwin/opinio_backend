package com.example.Opinio.posts.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookElementModel {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String url;
    private String image;
    private double price;
}
