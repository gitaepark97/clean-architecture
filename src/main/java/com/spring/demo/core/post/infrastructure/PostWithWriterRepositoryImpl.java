package com.spring.demo.core.post.infrastructure;

import com.spring.demo.core.post.application.port.PostWithWriterRepository;
import com.spring.demo.core.post.domain.PostWithWriter;
import com.spring.demo.core.post.infrastructure.entity.PostEntity;
import com.spring.demo.core.post.infrastructure.entity.PostEntityRepository;
import com.spring.demo.core.user.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
class PostWithWriterRepositoryImpl implements PostWithWriterRepository {

    private final PostEntityRepository postEntityRepository;

    @Override
    public Page<PostWithWriter> findAllByNicknameOrContent(String nickname, String content, Pageable pageable) {
        PostEntity postEntity = PostEntity.builder()
            .user(UserEntity.builder()
                .nickname(nickname)
                .build())
            .content(content)
            .build();

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreNullValues();
        Example<PostEntity> example = Example.of(postEntity, matcher);

        return postEntityRepository.findAll(example, pageable).map(PostEntity::toPostWithWriter);
    }

}
