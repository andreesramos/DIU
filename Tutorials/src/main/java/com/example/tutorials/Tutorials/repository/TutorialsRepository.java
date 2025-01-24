package com.example.tutorials.Tutorials.repository;

import com.example.tutorials.Tutorials.model.Tutorials;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TutorialsRepository extends MongoRepository<Tutorials,String> {
    List<Tutorials> findByPublishedTrue();
    List<Tutorials>  findAll();
    Optional<Tutorials> getTutorialById();
    List<Tutorials> findByTitleContaining(String title);
    List<Tutorials> findByPublished(boolean published);
}
