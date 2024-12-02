package com.spring.demo.core.post.web;

import com.spring.demo.core.post.application.PostService;
import com.spring.demo.core.post.web.request.CreatePostRequest;
import com.spring.demo.core.post.web.request.UpdatePostRequest;
import com.spring.demo.core.post.web.response.PostResponse;
import com.spring.demo.core.post.web.response.PostWithWriterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
class PostController {

    private final PostService postService;

    @PostMapping
    PostResponse createPost(@RequestParam Long userId, @RequestBody @Valid CreatePostRequest request) {
        return PostResponse.from(postService.createPost(userId, request.content()));
    }

    @GetMapping
    Page<PostWithWriterResponse> searchPostWithWriters(
        @RequestParam(required = false) String nickname,
        @RequestParam(required = false) String content,
        Pageable pageable
    ) {
        return postService.searchPostWithWriters(nickname, content, pageable).map(PostWithWriterResponse::from);
    }

    @PutMapping("/{postId}")
    PostResponse updatePost(
        @PathVariable Long postId,
        @RequestParam Long userId,
        @RequestBody @Valid UpdatePostRequest request
    ) {
        return PostResponse.from(postService.updatePost(postId, userId, request.content()));
    }

}
