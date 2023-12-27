package com.example.OpinioBackend.posts.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public int getViews(){
        if(reads==null) return 0;
        return reads.size() + views;
    }


    private Set<String> reads = Set.of();

    private String image;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PostElementModel> elements;
}
