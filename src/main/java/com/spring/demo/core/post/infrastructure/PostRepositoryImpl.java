package com.spring.demo.core.post.infrastructure;

import com.spring.demo.core.post.application.port.PostRepository;
import com.spring.demo.core.post.domain.Post;
import com.spring.demo.core.post.infrastructure.entity.PostEntity;
import com.spring.demo.core.post.infrastructure.entity.PostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
class PostRepositoryImpl implements PostRepository {

    private final PostEntityRepository postEntityRepository;

    @Override
    public Optional<Post> findById(Long id) {
        return postEntityRepository.findById(id).map(PostEntity::toPost);
    }

    @Override
    public Post save(Post post) {
        return postEntityRepository.save(PostEntity.from(post)).toPost();
    }

}
