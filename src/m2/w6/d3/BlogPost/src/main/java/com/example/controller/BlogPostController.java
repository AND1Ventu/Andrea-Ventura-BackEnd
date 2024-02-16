package com.example.controller;

import com.example.demo.model.BlogPost;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BlogPostController {

    @Autowired
    private PostService postService;

    @GetMapping("/blogPosts")
    public List<BlogPost> getAll(){
        return postService.cercaTuttiIBlogPost();
    }

    @GetMapping("/blogPosts/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable int id){
        try{
            BlogPost blogPost = postService.cercaBlogPostPerId(id);
            return new ResponseEntity<>(blogPost, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/blogPosts")
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
        try{
            BlogPost createdPost = postService.salvaBlogPost(blogPost);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED)
        }
    }

    @PutMapping("/blogPosts/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable int id, @RequestBody BlogPost blogPost){
        try{
            BlogPost updatedPost = postService.aggiornaBlogPost(id, blogPost);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/blogPosts/{id}")
    public ResponseEntity<void> deleteBlogPost(@PathVariable int id){
        if (postService.cancellaBlogPost(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
