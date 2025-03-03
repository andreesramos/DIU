package com.example.tutorials.Tutorials.service;

import com.example.tutorials.Tutorials.model.TutorialsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TutorialsService {
    List<TutorialsDto> getAllTutorials(); //funciona
    Optional<TutorialsDto> getTutorialById(String id); //funciona
    List<TutorialsDto> findByTitleContaining(String title);
    List<TutorialsDto> findByPublished(); //funciona
    TutorialsDto save(TutorialsDto tutorial); //funciona
    TutorialsDto updateTutorial(TutorialsDto tutorial); //funciona
    ResponseEntity deleteTutorial(String id); //funciona
    ResponseEntity deleteAllTutorials(); //funciona
}
