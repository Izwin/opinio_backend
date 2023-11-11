package com.example.OpinioBackend.posts.controller;

import com.example.OpinioBackend.posts.models.*;
import com.example.OpinioBackend.posts.service.PostsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;


    @GetMapping
    public ResponseEntity<List<PostModel>> getPosts(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size,@RequestParam(required = false) String search) {
        return ResponseEntity.ok().body(postsService.getPosts(page,size,search));
    }


    @GetMapping("/looks")
    public ResponseEntity<List<LookModel>> getLooks(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size,@RequestParam(required = false) String search) {
        return ResponseEntity.ok().body(postsService.getLooks(page,size,search));
    }

    @GetMapping("/{filename}")
    public ResponseEntity getImage(@PathVariable String filename) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(postsService.getImage(filename));
    }



}
