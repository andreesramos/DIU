package com.example.agenda.service;

import com.example.agenda.model.PersonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDto> getAllPersons(); //funciona
    Optional<PersonDto> getPersonById(String id); //funciona
    List<PersonDto> findByNombreContaining(String nombre);
    //List<PersonDto> findByPublished(); //funciona
    PersonDto save(PersonDto person); //funciona
    PersonDto updatePerson(PersonDto person); //funciona
    ResponseEntity deletePerson(String id); //funciona
    ResponseEntity deleteAllPersons(); //funciona
}
