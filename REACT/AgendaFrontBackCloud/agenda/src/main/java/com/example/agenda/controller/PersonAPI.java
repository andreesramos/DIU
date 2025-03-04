package com.example.agenda.controller;

import com.example.agenda.model.PersonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonAPI {
    List<PersonDto> getAllPersons();
    Optional<PersonDto> getPersonById(String id);
    List<PersonDto> findByNombreContaining(String nombre);
    //List<PersonDto>findByPublished();
    PersonDto save(PersonDto PersonDto);
    PersonDto updatePerson(PersonDto person, String id);
    ResponseEntity deletePerson(String id);
    ResponseEntity deleteAllPersons();
}
