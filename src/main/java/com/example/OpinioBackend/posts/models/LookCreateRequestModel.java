package com.example.OpinioBackend.posts.models;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookCreateRequestModel {
    private String title;
    private String description;
    private List<LookElementModel> elements;
}
