package com.lucas.performancetest.modules.post.service;

import com.lucas.performancetest.modules.post.domain.Post;
import com.lucas.performancetest.modules.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository repository;


    @Transactional(readOnly = true)
    public List<Post> findAllPost() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findByPostId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
