package com.example.service;

import com.example.model.Autore;
import com.example.model.BlogPost;
import com.example.repository.AutoreRepository;
import com.example.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private PostService postDao;

    public List<Autore> findAll() {
        return autoreRepository.findAll();
    }

    public Optional<Autore> getAuthorById(Long id) {
        return autoreRepository.findById(id);
    }

    public Autore saveAuthor(Autore author) {
        return autoreRepository.save(author);
    }

    public Autore updateAuthor(Long id, Autore authorDetails) {
        Optional<Autore> authorOptional = autoreRepository.findById(id);
        if (!authorOptional.isPresent()) {
            // Handle author not found case (e.g., return null or throw a different exception)
            // throw new Exception("Autore non trovato"); // Replace with appropriate exception
            return null; // Replace with desired behavior if author not found
        }

        Autore author = authorOptional.get();

        // Update author fields
        author.setNome(authorDetails.getNome());
        author.setCognome(authorDetails.getCognome());
        // ... (update other fields)

        return autoreRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        if (!autoreRepository.existsById(id)) {
            // Handle author not found case (e.g., log a warning)
            System.out.println("Autore con ID " + id + " non trovato");
            return;
        }
        autoreRepository.deleteById(id);
    }

    public List<BlogPost> getBlogPostsByAuthor(Long authorId) {
        return BlogPostRepository.findAllByAutore_Id(authorId);
    }
}