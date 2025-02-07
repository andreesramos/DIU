package com.example.agenda.controller.impl;

import com.example.agenda.controller.PersonAPI;
import com.example.agenda.model.PersonDto;
import com.example.agenda.repository.PersonRepository;
import com.example.agenda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PersonController implements PersonAPI {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Override
    @GetMapping("/agenda")
    public List<PersonDto> getAllPersons(){return personService.getAllPersons();}

    @Override
    @GetMapping("/agenda/{id}")
    public Optional<PersonDto> getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }

    @Override
    @GetMapping("/agenda/title/{title}")
    public List<PersonDto> findByNombreContaining(@PathVariable String nombre) {
        return personService.findByNombreContaining(nombre);
    }

    /*@Override
    @GetMapping("/tutorials/published")
    public List<PersonDto> findByPublished() {
        return personService.findByPublished();
    }*/

    @Override
    @PostMapping("/agenda")
    public PersonDto save(@RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @Override
    @PutMapping("/agenda/{id}")
    public PersonDto updatePerson(@RequestBody PersonDto person,@PathVariable String id) {
        return personService.updatePerson(person);
    }

    @Override
    @DeleteMapping("/agenda/{id}")
    public ResponseEntity deletePerson(@PathVariable String id) {
        return personService.deletePerson(id);
    }

    @Override
    @DeleteMapping("/agenda")
    public ResponseEntity deleteAllPersons() {
        return personService.deleteAllPersons();
    }
}
