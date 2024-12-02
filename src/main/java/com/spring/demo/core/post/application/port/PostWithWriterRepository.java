package com.spring.demo.core.post.application.port;

import com.spring.demo.core.post.domain.PostWithWriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostWithWriterRepository {

    Page<PostWithWriter> findAllByNicknameOrContent(String nickname, String content, Pageable pageable);

}
