package com.example.OpinioBackend.posts.controller;

import com.example.OpinioBackend.posts.models.*;
import com.example.OpinioBackend.posts.service.PostsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PostModel>> getPosts(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size) {
        return ResponseEntity.ok().body(postsService.getPosts(page,size));
    }

    @GetMapping("/looks")
    public ResponseEntity<List<LookModel>> getLooks(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size) {
        return ResponseEntity.ok().body(postsService.getLooks(page,size));
    }

    @PostMapping(value = "/add")
    public ResponseEntity addPost(@RequestPart String json,@RequestPart List<MultipartFile> files,@RequestPart MultipartFile image ) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        PostCreateRequestModel postCreateRequestModel = objectMapper.readValue(json, PostCreateRequestModel.class);

        return ResponseEntity.ok().body(postsService.addPost(postCreateRequestModel,files,image));
    }

    @GetMapping("/{filename}")
    public ResponseEntity getImage(@PathVariable String filename) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(postsService.getImage(filename));
    }

    @PostMapping(value = "/add_look")
    public ResponseEntity addLook(@RequestPart String json,@RequestPart List<MultipartFile> files,@RequestPart MultipartFile image ) throws IOException {
        System.out.println(123);
        ObjectMapper objectMapper = new ObjectMapper();
        LookCreateRequestModel lookCreateRequestModel = objectMapper.readValue(json, LookCreateRequestModel.class);


        return ResponseEntity.ok().body(postsService.addLook(lookCreateRequestModel,files,image));
    }

}
