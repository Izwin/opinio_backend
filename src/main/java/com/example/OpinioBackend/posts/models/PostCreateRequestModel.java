package com.example.OpinioBackend.posts.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostCreateRequestModel {
    public String title;
    public List<PostElementRequestModel> elements;

}
