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


}
