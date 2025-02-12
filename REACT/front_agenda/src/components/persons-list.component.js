import React, { useState, useEffect } from "react";
import AgendaDataService from "../services/agenda.service";
import { Link } from "react-router-dom";

const PersonsList = () => {
  const [persons, setPersons] = useState([]);
  const [currentPerson, setCurrentPerson] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchNombre, setSearchPerson] = useState("");

  useEffect(() => {
    retrievePersons();
  }, []);

  const onChangeSearchNombre = (e) => {
    setSearchPerson(e.target.value);
  };

  const retrievePersons = () => {
    AgendaDataService.getAll()
      .then((response) => {
        setPersons(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const refreshList = () => {
    retrievePersons();
    setCurrentPerson(null);
    setCurrentIndex(-1);
  };

  const setActivePerson = (person, index) => {
    setCurrentPerson(person);
    setCurrentIndex(index);
  };

  const removeAllPersons = () => {
    AgendaDataService.deleteAll()
      .then((response) => {
        console.log(response.data);
        refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const searchNombreHandler = () => {
    AgendaDataService.findByNombre(searchNombre)
      .then((response) => {
        setPersons(response.data);
        console.log(response.data);
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
            placeholder="Search by nombre"
            value={searchNombre}
            onChange={onChangeSearchNombre}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={searchNombreHandler}
            >
              Search
            </button>
          </div>
        </div>
      </div>
      <div className="col-md-6">
        <h4>Persons List</h4>
        <ul className="list-group">
          {persons &&
            persons.map((person, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => setActivePerson(person, index)}
                key={index}
              >
                {person.title}
              </li>
            ))}
        </ul>

        <button
          className="m-3 btn btn-sm btn-danger"
          onClick={removeAllPersons}
        >
          Remove All
        </button>
      </div>
      <div className="col-md-6">
        {currentPerson ? (
          <div>
            <h4>Person</h4>
            <div>
              <label>
                <strong>Nombre:</strong>
              </label>{" "}
              {currentPerson.nombre}
            </div>
            <div>
              <label>
                <strong>Apellidos:</strong>
              </label>{" "}
              {currentPerson.apellidos}
            </div>
            <div>
              <label>
                <strong>Calle:</strong>
              </label>{" "}
              {currentPerson.calle}
            </div>
            <div>
              <label>
                <strong>Codigo Postal:</strong>
              </label>{" "}
              {currentPerson.codigoPostal}
            </div>
            <div>
              <label>
                <strong>Ciudad:</strong>
              </label>{" "}
              {currentPerson.ciudad}
            </div>
            <div>
              <label>
                <strong>Fecha Nacimiento:</strong>
              </label>{" "}
              {currentPerson.fechaNacimiento}
            </div>
            {/*<div>
              <label>
                <strong>Tutoriales:</strong>
              </label>{" "}
              {currentPerson.tutoriales}
            </div>*/}

            <Link
              to={"/agenda/" + currentPerson.id}
              className="badge badge-warning"
            >
              Edit
            </Link>
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
