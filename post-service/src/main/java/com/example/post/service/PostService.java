package com.example.post.service;

import com.example.post.dto.PageResponse;
import com.example.post.dto.request.PostRequest;
import com.example.post.dto.response.PostResponse;
import com.example.post.dto.response.UserResponse;
import com.example.post.entity.Post;
import com.example.post.mapper.PostMapper;
import com.example.post.repository.PostRepository;
import com.example.post.repository.httpclient.ProfileClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;
    ProfileClient profileClient;

    public PostResponse createPost(PostRequest request){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = Post.builder()
                .userId(userId)
                .content(request.getContent())
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .build();
        postRepository.save(post);
        return postMapper.toPostResponse(post);
    }
    public PageResponse<PostResponse> getMyPosts(int page, int size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        UserResponse userResponse = null;
        try {
            userResponse = profileClient.getMyInfo().getResult();
        }catch (Exception e){
            log.error("Error while getting user profile", e);
        }
        Sort sort = Sort.by("createdDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        var pageData = postRepository.findAllByUserId(userId,pageable);
        String username =userResponse!= null ? userResponse.getUsername():null;
        var postList = pageData.map(post -> {
            var res = postMapper.toPostResponse(post);
            res.setCreated("");
            res.setUsername(username);
            return res;
        }).getContent();
        return PageResponse.<PostResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(size)
                .totalElements(pageData.getTotalElements())
                .data(postList)  // bat buoc
                .build();
    }
}
