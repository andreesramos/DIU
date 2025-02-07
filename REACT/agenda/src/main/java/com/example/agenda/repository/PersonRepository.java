package com.example.agenda.repository;

import com.example.agenda.model.PersonVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<PersonVO,String> {
    //List<PersonVO> findByPublishedTrue();
    List<PersonVO>  findAll();
    Optional<PersonVO> getPersonById();
    List<PersonVO> findByNombreContaining(String nombre);
    //List<PersonVO> findByPublished(boolean published);
}
