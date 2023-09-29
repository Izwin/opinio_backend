package com.example.OpinioBackend.posts.service;

import com.example.OpinioBackend.posts.models.*;
import com.example.OpinioBackend.posts.repository.LooksRepository;
import com.example.OpinioBackend.posts.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final LooksRepository looksRepository;
    private final String imageLocation = "images/profile/";

    public List<PostModel> getPosts() {
        return postsRepository.findAll();
    }
    public List<LookModel> getLooks() {
        return looksRepository.findAll();
    }
    public boolean addPost(PostCreateRequestModel postCreateRequestModel, List<MultipartFile> files, MultipartFile image) throws IOException {
        PostModel postModel = PostModel.builder().title(postCreateRequestModel.getTitle()).published(new Date(System.currentTimeMillis())).build();

        String filename = postCreateRequestModel.getTitle() + System.currentTimeMillis() + ".jpg";
        Path path = Path.of(imageLocation + filename);
        Files.write(path, image.getBytes());


        postModel.setImage(filename);
        List<PostElementModel> elements = new ArrayList<>();

        for (PostElementRequestModel postElementRequestModel : postCreateRequestModel.getElements()) {
            switch (postElementRequestModel.getPostElementType()) {
                case IMAGE:
                    String postElementFilename = postElementRequestModel.hashCode() + System.currentTimeMillis() + ".jpg";
                    Path postElementpath = Path.of(imageLocation + postElementFilename);
                    MultipartFile multipartFile = files.stream().filter(f -> f.getOriginalFilename().equals(postElementRequestModel.getContent())).toList().get(0);
                    Files.write(postElementpath, multipartFile.getBytes());

                    PostElementModel postElementModel = PostElementModel.builder().postElementType(PostElementType.IMAGE).content(postElementFilename).build();
                    elements.add(postElementModel);
                    break;
                case TITLE:
                    PostElementModel titleElementModel = PostElementModel.builder().postElementType(PostElementType.TITLE).content(postElementRequestModel.getContent()).build();
                    elements.add(titleElementModel);
                    break;

            }
        }

        postModel.setElements(elements);
        postsRepository.save(postModel);
        return true;
    }

    public boolean addLook(LookCreateRequestModel lookCreateRequestModel, List<MultipartFile> files, MultipartFile image) throws IOException {
        LookModel lookModel = LookModel.builder()
                .title(lookCreateRequestModel.getTitle())
                .description(lookCreateRequestModel.getDescription())
                .published(new Date(System.currentTimeMillis()))
                .build();

        String filename = lookCreateRequestModel.getTitle() + System.currentTimeMillis() + ".jpg";
        Path path = Path.of(imageLocation + filename);
        Files.write(path, image.getBytes());


        lookModel.setImage(filename);

        List<LookElementModel> elements = new ArrayList<>();

        for (LookElementModel lookElement : lookCreateRequestModel.getElements()) {
            LookElementModel lookElementModel = LookElementModel.builder().url(lookElement.getUrl()).price(lookElement.getPrice()).title(lookElement.getTitle()).build();
            String lookElementFilename = lookElement.hashCode() + System.currentTimeMillis() + ".jpg";
            Path lookElementpath = Path.of(imageLocation + lookElementFilename);
            MultipartFile multipartFile = files.stream().filter(f -> f.getOriginalFilename().equals(lookElement.getImage())).toList().get(0);
            Files.write(lookElementpath, multipartFile.getBytes());

            lookElementModel.setImage(lookElementFilename);
            elements.add(lookElementModel);
        }

        lookModel.setElements(elements);
        looksRepository.save(lookModel);
        return true;
    }

    public Resource getImage(String filename) {
        Path path = Path.of(imageLocation + filename);
        Resource resource = UrlResource.from(path.toUri());
        return resource;
    }
}
