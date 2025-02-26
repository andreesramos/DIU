import React, { useState, useEffect } from "react";
import { usePersons } from "../context/PersonContext";
import { useAuth } from "../context/AuthContext";
import AgendaDataService from "../services/agenda.service";
import { Link } from "react-router-dom";
import ButtonDelete from "./ButtonDelete";
import "../styles/PersonsList.css";

const PersonsList = () => {
  const { persons, setPersons, currentPerson, setCurrentPerson, currentIndex, setCurrentIndex } = usePersons();
  const [searchNombre, setSearchNombre] = useState("");
  const { user } = useAuth();
  const [originalPersons, setOriginalPersons] = useState([]);

  useEffect(() => {
    retrievePersons();
  }, []);

  const onChangeSearchNombre = (e) => {
    const searchTerm = e.target.value.toLowerCase();
    setSearchNombre(searchTerm);

    if (searchTerm === "") {
        setPersons(originalPersons); // Restaurar la lista original si está vacío
    } else {
        const filteredPersons = originalPersons.filter((person) =>
            person.nombre.toLowerCase().includes(searchTerm)
        );
        setPersons(filteredPersons); // Actualizar la lista con la búsqueda
    }
};

  const searchNombreHandler = () => {
    AgendaDataService.findByNombre(searchNombre)
      .then((response) => {
        setPersons(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const retrievePersons = async () => {
    try {
        const response = await AgendaDataService.getAll();
        setPersons(response.data); 
        setOriginalPersons(response.data);
    } catch (error) {
        console.error("Error retrieving persons:", error);
    }
};

  // const refreshList = () => {
  //   retrievePersons();
  //   setCurrentPerson(null);
  //   setCurrentIndex(-1);
  // };

  const setActivePerson = (person, index) => {
    setCurrentPerson(person);
    setCurrentIndex(index);
  };

  const removeAllPersons = () => {
    AgendaDataService.deleteAll()
      .then(() => {
        setPersons([]);
        setCurrentPerson(null);
        setCurrentIndex(-1);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Buscar por nombre"
            value={searchNombre}
            onChange={onChangeSearchNombre}
          />
        </div>
      </div>

      <div className="col-md-6">
        <h4>Persons List</h4>
        <ul className="list-group">
          {persons &&
            persons.map((person, index) => (
              <li
                key={person.id}
                className={"list-group-item " + (index === currentIndex ? "active" : "")}
                onClick={() => setActivePerson(person, index)}
              >
                {person.nombre}
                {/* <ButtonDelete personId={person.id} /> */}
              </li>
            ))}
        </ul>

        <button className="m-3 btn btn-sm btn-danger" onClick={removeAllPersons}>
          Remove All
        </button>
      </div>

      <div className="col-md-6">
        {currentPerson ? (
          <div>
            <h4>Person Details</h4>
            <div>
              <label><strong>Nombre:</strong></label> {currentPerson.nombre}
            </div>
            <div>
              <label><strong>Apellidos:</strong></label> {currentPerson.apellidos}
            </div>
            <div>
              <label><strong>Calle:</strong></label> {currentPerson.calle}
            </div>
            <div>
              <label><strong>Codigo Postal:</strong></label> {currentPerson.codigoPostal}
            </div>
            <div>
              <label><strong>Ciudad:</strong></label> {currentPerson.ciudad}
            </div>
            <div>
              <label><strong>Fecha Nacimiento:</strong></label> {currentPerson.fechaNacimiento}
            </div>
      
            <div>
              {currentPerson.tutoriales && currentPerson.tutoriales.length > 0 ? (
                <Link to="/tutoriales" className="btn btn-primary btn-sm">
                  Ver Tutoriales
                </Link>
              ) : (
                <p>No tiene tutoriales asignados</p>
              )}
            </div>

            {user && (
              <div className="flex-container">
                <Link to={`/agenda/${currentPerson.id}`} className="btn btn-warning btn-sm">
                  Edit
                </Link>

                <ButtonDelete personId={currentPerson.id} />
              </div>
            )}
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Person...</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default PersonsList;