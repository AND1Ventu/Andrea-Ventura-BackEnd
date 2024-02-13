package com.example.demo.service;

import com.example.demo.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    private List<BlogPost> posts = new ArrayList<>();

    @Autowired
    private AutoreService autoreService;

    public List<BlogPost> cercaTuttiIBlogPost(){
        return posts;
    }

    public BlogPost cercaBlogPostPerId(int id) throws NoSuchElementException {
        Optional<BlogPost> p = posts.stream().filter(post -> post.getId()==id).findAny();

        if(p.isPresent()){
            return p.get();
        }
        else{
            throw new NoSuchElementException("BlogPost non trovata");
        }
    }

    public BlogPost salvaBlogPost(BlogPost post){
        posts.add(post);
        return post;
    }

    public BlogPost aggiornaBlogPost(int id, BlogPost post) throws NoSuchElementException{
        BlogPost p = cercaBlogPostPerId(id);

        p.setAutore(post.getAutore());
        p.setContenuto(post.getContenuto());
        p.setCategoria(post.getCategoria());
        p.setCover(post.getCover());
        p.setTitolo(post.getTitolo());
        p.setTempoDiLettura(post.getTempoDiLettura());

        return p;
    }

    public Boolean cancellaBlogPost(int id) throws NoSuchElementException{
        BlogPost p = cercaBlogPostPerId(id);

        posts.remove(p);
        return true;
    }
}
