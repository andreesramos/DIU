package com.example.tutorials.Tutorials.controller.impl;

import com.example.tutorials.Tutorials.controller.TutorialsAPI;
import com.example.tutorials.Tutorials.model.TutorialsDto;
import com.example.tutorials.Tutorials.repository.TutorialsRepository;
import com.example.tutorials.Tutorials.service.TutorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TutorialsController implements TutorialsAPI {
    @Autowired
    private TutorialsService tutorialsService;
    @Autowired
    private TutorialsRepository tutorialsRepository;
    @Override
    @GetMapping("/tutorials")
    public List<TutorialsDto> getAllTutorials(){return tutorialsService.getAllTutorials();}

    @Override
    @GetMapping("/tutorials/{id}")
    public Optional<TutorialsDto> getTutorialById(@PathVariable String id) {
        return tutorialsService.getTutorialById(id);
    }

    @Override
    @GetMapping("/tutorials/title/{title}")
    public List<TutorialsDto> findByTitleContaining(@PathVariable String title) {
        return tutorialsService.findByTitleContaining(title);
    }

    @Override
    @GetMapping("/tutorials/published")
    public List<TutorialsDto> findByPublished() {
        return tutorialsService.findByPublished();
    }

    @Override
    @PostMapping("/tutorials")
    public TutorialsDto save(@RequestBody TutorialsDto tutorialDto) {
        return tutorialsService.save(tutorialDto);
    }

    @Override
    @PutMapping("/tutorials/{id}")
    public TutorialsDto updateTutorial(@RequestBody TutorialsDto tutorial,@PathVariable String id) {
        return tutorialsService.updateTutorial(tutorial);
    }

    @Override
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity deleteTutorial(@PathVariable String id) {
        return tutorialsService.deleteTutorial(id);
    }

    @Override
    @DeleteMapping("/tutorials")
    public ResponseEntity deleteAllTutorials() {
        return tutorialsService.deleteAllTutorials();
    }
}
