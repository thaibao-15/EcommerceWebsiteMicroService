package com.example.post.controller;

import com.example.post.dto.ApiResponse;
import com.example.post.dto.PageResponse;
import com.example.post.dto.request.PostRequest;
import com.example.post.dto.response.PostResponse;
import com.example.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;
    @PostMapping("/create")
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest request){
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request))
                .build();
    }
    @GetMapping("/my-posts")
    public ApiResponse<PageResponse<PostResponse>> getAllPost(
        @RequestParam(value = "page",required = false,defaultValue = "1") int page,
        @RequestParam(value = "size", required = false,defaultValue = "10") int size
    ){
        return ApiResponse.<PageResponse<PostResponse>>builder()
                .result(postService.getMyPosts(page,size))
                .build();
    }
}
