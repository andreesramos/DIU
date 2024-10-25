package com.example.agenda.modelo;

import com.example.agenda.modelo.repository.PersonRepository;
import com.example.agenda.modelo.repository.impl.PersonRepositoryImpl;
import com.example.agenda.modelo.utilidad.PersonUtil;
import com.example.agenda.vista.Person;

import java.util.ArrayList;

public class AgendaModelo {
    PersonRepository personRepository;
    PersonUtil personUtil;

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ArrayList<PersonVO> obtenerPersonas() {
        ArrayList<PersonVO> listaPersons=personRepository.ObtenerListaPersonas();
        return listaPersons;
    }

    public ArrayList<Person> mostrarPersonas() {
        ArrayList<PersonVO> listaPersonsVO=obtenerPersonas();
        ArrayList<Person> listaPersonas=new ArrayList<>();
        listaPersonas=personUtil.getPerson(listaPersonsVO);
        return listaPersonas;
    }

    //Llamar al metodo de personRepositoryImpl addPersona

    //Controler llama al metodo de arriba
}
