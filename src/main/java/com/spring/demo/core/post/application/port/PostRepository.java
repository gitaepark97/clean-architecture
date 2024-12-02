package com.spring.demo.core.post.application.port;

import com.spring.demo.core.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(Long id);

    Post save(Post post);

}
