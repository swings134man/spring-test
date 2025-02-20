package com.lucas.performancetest.modules.post.repository;

import com.lucas.performancetest.modules.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
