package com.example.agenda.modelo.repository;

import com.example.agenda.modelo.PersonVO;
import com.example.agenda.modelo.ExcepcionPerson;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO> ObtenerListaMonedas() throws ExcepcionPerson;

    void addPersona(PersonVO var1) throws ExcepcionPerson;

    void deletePersona(Integer var1) throws ExcepcionPerson;

    void editPersona(PersonVO var1) throws ExcepcionPerson;

    int lastId() throws ExcepcionPerson;
}
