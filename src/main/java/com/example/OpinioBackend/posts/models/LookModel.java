package com.example.OpinioBackend.posts.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @ColumnDefault(value = "0")
    private int views;

    private String image;

    private Date published;

    public int getViews(){
        if(reads==null) return 0;
        return reads.size() + views;
    }


    private Set<String> reads = Set.of();;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LookElementModel> elements;
}
