package com.example.agenda.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PersonDto {

    private String id;
    private String nombre;
    private String apellidos;
    private String calle;
    private int codigoPostal;
    private String ciudad;
    private LocalDate fechaNacimiento;
    private String[] tutoriales;

}

