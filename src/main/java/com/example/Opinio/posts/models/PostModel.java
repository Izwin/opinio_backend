package com.example.Opinio.posts.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostModel {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Date published;

    private int views;

    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PostElementModel> elements;
}
