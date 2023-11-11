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
public class PostEditRequestModel {
    public String title;
    public int views;
    public List<PostElementRequestModel> elements;

}
