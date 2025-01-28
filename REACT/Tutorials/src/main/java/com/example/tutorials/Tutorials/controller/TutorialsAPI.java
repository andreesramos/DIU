package com.example.tutorials.Tutorials.controller;

import com.example.tutorials.Tutorials.model.TutorialsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TutorialsAPI {
    List<TutorialsDto> getAllTutorials();
    Optional<TutorialsDto> getTutorialById(String id);
    List<TutorialsDto> findByTitleContaining(String id);
    List<TutorialsDto>findByPublished();
    TutorialsDto save(TutorialsDto Tutorial);
    TutorialsDto updateTutorial(TutorialsDto tutorial, String id);
    ResponseEntity deleteTutorial(String id);
    ResponseEntity deleteAllTutorials();
}
