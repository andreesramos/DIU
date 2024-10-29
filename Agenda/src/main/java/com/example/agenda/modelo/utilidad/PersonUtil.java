package com.example.agenda.modelo.utilidad;

import com.example.agenda.modelo.PersonVO;
import com.example.agenda.vista.Person;

import java.util.ArrayList;

public class PersonUtil {

    public static ArrayList<PersonVO> getPersonVO(ArrayList<Person> personas) {
        ArrayList<PersonVO> personVOs = new ArrayList<>();
        for(Person persona : personas) {
            personVOs.add(new PersonVO(persona.getCodigo(), persona.getFirstName(), persona.getLastName(), persona.getStreet(), persona.getPostalCode(), persona.getCity(), persona.getBirthday()));
        }
        return personVOs;
    }

    public static ArrayList<Person> getPerson(ArrayList<PersonVO> personVOs) {
        ArrayList<Person> persons = new ArrayList<>();
        for(PersonVO personVO : personVOs) {
            persons.add(new Person(personVO.getCodigo(), personVO.getFirstName(), personVO.getLastName(), personVO.getStreet(), personVO.getPostalCode(), personVO.getCity(), personVO.getBirthday()));
        }
        return persons;
    }

    public static Person getPerson(PersonVO personVO) {
        Person person = new Person();
        person.setCodigo(personVO.getCodigo());
        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setStreet(personVO.getStreet());
        person.setPostalCode(personVO.getPostalCode());
        person.setCity(personVO.getCity());
        person.setBirthday(personVO.getBirthday());
        return person;
    }

    public static PersonVO getPersonVO(Person person) {
        PersonVO personVO = new PersonVO();
        personVO.setCodigo(person.getCodigo());
        personVO.setFirstName(person.getFirstName());
        personVO.setLastName(person.getLastName());
        personVO.setStreet(person.getStreet());
        personVO.setPostalCode(person.getPostalCode());
        personVO.setCity(person.getCity());
        personVO.setBirthday(person.getBirthday());
        return personVO;
    }
}
