package com.lucas.performancetest.modules.post.controller;

import com.lucas.performancetest.modules.post.domain.Post;
import com.lucas.performancetest.modules.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> findAllPost() {
        return new ResponseEntity<>(service.findAllPost(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findByPostId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findByPostId(id), HttpStatus.OK);
    }
}
