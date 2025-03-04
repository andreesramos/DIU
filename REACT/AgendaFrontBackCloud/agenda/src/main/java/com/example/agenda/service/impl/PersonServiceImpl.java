package com.example.agenda.service.impl;

import com.example.agenda.model.PersonDto;
import com.example.agenda.model.PersonVO;
import com.example.agenda.repository.PersonRepository;
import com.example.agenda.service.PersonService;
import com.example.agenda.util.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDto> getAllPersons(){
        List<PersonVO> personVOList = personRepository.findAll();
        return personVOList.stream()
                .map(PersonMapper::personMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonDto> getPersonById(String id) {
        Optional<PersonVO> personOptional = personRepository.findById(id);

        return personOptional.map(PersonMapper::personMapperEntityToDto);
    }

    @Override
    public List<PersonDto> findByNombreContaining(String nombre) {
        List<PersonVO> personOptional = personRepository.findByNombreContaining(nombre);

        return PersonMapper.personListMapperEntityToDto(personOptional);
    }

    /*@Override
    public List<TutorialsDto> findByPublished() {
        List<TutorialsVO> publishedTutorials = tutorialsRepository.findByPublished(true);

        return TutorialsMapper.tutorialsListMapperEntityToDto(publishedTutorials);
    }*/

    @Override
    public PersonDto save(PersonDto personDto) {
        PersonVO personVO = PersonMapper.personMapperDtoToEntity(personDto);
        PersonVO savedPersonEntity = personRepository.save(personVO);
        return PersonMapper.personMapperEntityToDto(savedPersonEntity);
    }

    @Override
    public PersonDto updatePerson(PersonDto person) {
        Optional<PersonVO> existingPersonOptional = personRepository.findById(person.getId());

        if (existingPersonOptional.isPresent()) {
            PersonVO existingPerson = existingPersonOptional.get();
            existingPerson.setNombre(person.getNombre());
            existingPerson.setApellidos(person.getApellidos());
            existingPerson.setCalle(person.getCalle());
            existingPerson.setCodigoPostal(person.getCodigoPostal());
            existingPerson.setCiudad(person.getCiudad());
            existingPerson.setFechaNacimiento(person.getFechaNacimiento());
            existingPerson.setTutoriales(person.getTutoriales());

            PersonVO updatedPerson = personRepository.save(existingPerson);

            return PersonMapper.personMapperEntityToDto(updatedPerson);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity deletePerson(String id) {
        try {
            Optional<PersonVO> existingPersonOptional = personRepository.findById(id);
            if (existingPersonOptional.isPresent()) {
                personRepository.deleteById(id);
                return ResponseEntity.ok("Persona eliminada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la persona");
        }
    }

    @Override
    public ResponseEntity deleteAllPersons() {
        personRepository.deleteAll();
        ResponseEntity.ok("Persona eliminada exitosamente");
        return ResponseEntity.ok().build();
    }
}
