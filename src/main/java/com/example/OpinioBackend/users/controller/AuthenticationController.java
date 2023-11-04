package com.example.OpinioBackend.users.controller;

import com.example.OpinioBackend.posts.models.LookCreateRequestModel;
import com.example.OpinioBackend.posts.models.PostCreateRequestModel;
import com.example.OpinioBackend.posts.service.PostsService;
import com.example.OpinioBackend.users.model.AuthenticationRequest;
import com.example.OpinioBackend.users.model.RefreshTokenRequest;
import com.example.OpinioBackend.users.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PostsService postsService;


    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        //authenticationService.addUser("admin","1234");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(
            @RequestBody RefreshTokenRequest request
    ) {
        return ResponseEntity.ok(authenticationService.refresh(request));

    }

    @PostMapping(value = "/add")
    public ResponseEntity addPost(@RequestPart String json,@RequestPart List<MultipartFile> files,@RequestPart MultipartFile image ) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        PostCreateRequestModel postCreateRequestModel = objectMapper.readValue(json, PostCreateRequestModel.class);

        return ResponseEntity.ok().body(postsService.addPost(postCreateRequestModel,files,image));
    }

    @PostMapping(value = "/add_look")
    public ResponseEntity addLook(@RequestPart LookCreateRequestModel json, @RequestPart List<MultipartFile> files, @RequestPart MultipartFile image ) throws IOException {
        System.out.println(123);


        return ResponseEntity.ok().body(postsService.addLook(json,files,image));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteById(@PathVariable long id){
        return ResponseEntity.ok(postsService.deletePost(id));

    }
    @DeleteMapping("delete_look/{id}")
    public ResponseEntity deleteLookById(@PathVariable long id){
        return ResponseEntity.ok(postsService.deleteLook(id));

    }


}
