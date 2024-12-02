package com.spring.demo.core.post.infrastructure.entity;

import com.spring.demo.core.post.domain.Post;
import com.spring.demo.core.post.domain.PostWithWriter;
import com.spring.demo.core.user.infrastructure.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"post\"")
@DynamicInsert
@DynamicUpdate
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private String content;

    private Long createdAt;

    private Long updatedAt;

    public static PostEntity from(Post post) {
        return PostEntity.builder()
            .id(post.id())
            .user(UserEntity.builder()
                .id(post.writerId())
                .build())
            .content(post.content())
            .createdAt(post.createdAt())
            .updatedAt(post.updatedAt())
            .build();
    }

    public Post toPost() {
        return Post.builder()
            .id(id)
            .writerId(user.getId())
            .content(content)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

    public PostWithWriter toPostWithWriter() {
        return PostWithWriter.from(toPost(), user.toUser());
    }

}
