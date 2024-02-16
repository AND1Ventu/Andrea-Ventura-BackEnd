package com.example.service;

import com.example.model.Autore;
import com.example.model.BlogPost;
import com.example.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    @Autowired
    private BlogPostRepository postRepository;

    @Autowired
    private AutoreService autoreService;

    public List<BlogPost> getAllBlogPosts() {
        return postRepository.findAll();
    }

    public Optional<BlogPost> getBlogPostById(int id) {
        return postRepository.findById(id);
    }

    public BlogPost saveBlogPost(BlogPost post) {
        // Validate post if needed (e.g., check required fields)
        return postRepository.save(post);
    }

    public Optional<BlogPost> updateBlogPost(int id, BlogPost updatedPost) {
        Optional<BlogPost> existingPost = postRepository.findById(id);
        if (!existingPost.isPresent()) {
            return Optional.empty(); // Or handle not found case differently
        }

        BlogPost post = existingPost.get();

        // Update post fields (only if non-null in updatedPost)
        post.setTitolo(updatedPost.getTitolo() != null ? updatedPost.getTitolo() : post.getTitolo());
        post.setContenuto(updatedPost.getContenuto() != null ? updatedPost.getContenuto() : post.getContenuto());
        // ... (update other fields)

        Optional<Autore> author = autoreService.getAuthorById(updatedPost.getAutore().getId());
        if (!author.isPresent()) {
            // Handle author not found case (e.g., log a warning, return error, etc.)
            System.out.println("Autore con ID " + updatedPost.getAutore().getId() + " non trovato");
            return Optional.empty(); // Or handle differently
        }

        post.setAutore(author.get());

        return Optional.of(postRepository.save(post));
    }

    public void deleteBlogPost(int id) {
        if (!postRepository.existsById(id)) {
            // Handle post not found case (e.g., log a warning)
            System.out.println("BlogPost con ID " + id + " non trovata");
            return;
        }
        postRepository.deleteById(id);
    }
}
