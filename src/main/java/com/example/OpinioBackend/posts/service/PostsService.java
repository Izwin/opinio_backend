package com.example.OpinioBackend.posts.service;

import com.example.OpinioBackend.posts.models.*;
import com.example.OpinioBackend.posts.repository.LooksRepository;
import com.example.OpinioBackend.posts.repository.PostsRepository;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final LooksRepository looksRepository;

    @Value("${gcp.bucket.name}")
    private String bucketName;

    @Autowired
    Storage storage;

    public List<PostModel> getPosts(int page,int size) {
        var list =  postsRepository.findAll(PageRequest.of(page,size)).stream().toList();
        return list;
    }
    public List<LookModel> getLooks(int page,int size) {
        return looksRepository.findAll(PageRequest.of(page,size)).stream().toList();
    }
    public boolean addPost(PostCreateRequestModel postCreateRequestModel, List<MultipartFile> files, MultipartFile image) throws IOException {
        PostModel postModel = PostModel.builder().title(postCreateRequestModel.getTitle()).published(new Date(System.currentTimeMillis())).build();

        String filename = postCreateRequestModel.getTitle().hashCode() + System.currentTimeMillis() + ".jpg";
        BlobId blobId = BlobId.of(bucketName, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).
                setContentType(image.getContentType()).build();
        Blob blob = storage.create(blobInfo,image.getBytes());


        postModel.setImage(filename);
        List<PostElementModel> elements = new ArrayList<>();

        for (PostElementRequestModel postElementRequestModel : postCreateRequestModel.getElements()) {
            switch (postElementRequestModel.getPostElementType()) {
                case IMAGE:
                    String postElementFilename = postElementRequestModel.hashCode() + System.currentTimeMillis() + ".jpg";
                    MultipartFile multipartFile = files.stream().filter(f -> Objects.equals(f.getOriginalFilename(), postElementRequestModel.getContent())).toList().get(0);

                    BlobId imageFileBlobId = BlobId.of(bucketName, postElementFilename);
                    BlobInfo imageFileBlobInfo = BlobInfo.newBuilder(blobId).
                            setContentType(multipartFile.getContentType()).build();
                    Blob imafeFileBlob = storage.create(blobInfo,multipartFile.getBytes());

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
        BlobId blobId = BlobId.of(bucketName, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).
                setContentType(image.getContentType()).build();
        Blob blob = storage.create(blobInfo,image.getBytes());


        lookModel.setImage(filename);

        List<LookElementModel> elements = new ArrayList<>();

        for (LookElementModel lookElement : lookCreateRequestModel.getElements()) {
            LookElementModel lookElementModel = LookElementModel.builder().url(lookElement.getUrl()).price(lookElement.getPrice()).title(lookElement.getTitle()).build();
            String lookElementFilename = lookElement.hashCode() + System.currentTimeMillis() + ".jpg";
            MultipartFile multipartFile = files.stream().filter(f -> f.getOriginalFilename().equals(lookElement.getImage())).toList().get(0);
            BlobId imageFileBlobId = BlobId.of(bucketName, multipartFile.getOriginalFilename());
            BlobInfo imageFileBlobInfo = BlobInfo.newBuilder(blobId).
                    setContentType(multipartFile.getContentType()).build();
            Blob imafeFileBlob = storage.create(blobInfo,multipartFile.getBytes());

            lookElementModel.setImage(lookElementFilename);
            elements.add(lookElementModel);
        }

        lookModel.setElements(elements);
        looksRepository.save(lookModel);
        return true;
    }

    public Resource getImage(String filename) throws IOException {

        Blob blob = storage.get(bucketName, filename);
        ByteArrayResource resource = new ByteArrayResource(
                blob.getContent());

        return resource;
    }
}
