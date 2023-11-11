package com.example.OpinioBackend.posts.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LookEditRequestModel {
    public String title;
    public String description;
    public int views;
    public List<LookElementModel> elements;

}
