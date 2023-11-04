package com.example.OpinioBackend.posts.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookModel {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String image;

    private Date published;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LookElementModel> elements;
}
