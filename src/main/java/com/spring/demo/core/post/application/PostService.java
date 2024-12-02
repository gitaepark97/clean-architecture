package com.spring.demo.core.post.application;

import com.spring.demo.core.post.application.port.PostRepository;
import com.spring.demo.core.post.application.port.PostWithWriterRepository;
import com.spring.demo.core.post.domain.Post;
import com.spring.demo.core.post.domain.PostWithWriter;
import com.spring.demo.core.user.application.UserService;
import com.spring.demo.global.exception.ApplicationException;
import com.spring.demo.global.exception.ErrorCode;
import com.spring.demo.global.provider.port.ClockProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostService {

    private final ClockProvider clockProvider;
    private final PostRepository postRepository;
    private final PostWithWriterRepository postWithWriterRepository;
    private final UserService userService;

    public Page<PostWithWriter> searchPostWithWriters(String nickname, String content, Pageable pageable) {
        return postWithWriterRepository.findAllByNicknameOrContent(nickname, content, pageable);
    }

    public Post createPost(Long writerId, String content) {
        userService.validateExistUser(writerId);

        Post post = Post.create(writerId, content, clockProvider);
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Long writerId, String content) {
        Post post = getPost(postId);

        post = post.update(writerId, content, clockProvider);
        return postRepository.save(post);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_POST, Map.of("postId", postId)));
    }

}
