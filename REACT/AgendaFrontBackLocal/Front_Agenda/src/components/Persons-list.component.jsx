import React, { useState, useEffect } from "react";
import { usePersons } from "../context/PersonContext";
import { useAuth } from "../context/UserContext";
import AgendaDataService from "../services/agenda.service";
import TutorialDataService from "../services/tutorial.service";
import { Link } from "react-router-dom";
import ButtonDelete from "./ButtonDelete";
import "../styles/PersonsList.css";

const MAX_PERSONS = 50;

const PersonsList = () => {
  const {
    persons,
    setPersons,
    currentPerson,
    setCurrentPerson,
    currentIndex,
    setCurrentIndex,
  } = usePersons();
  const [searchNombre, setSearchNombre] = useState("");
  const { user } = useAuth();
  const [originalPersons, setOriginalPersons] = useState([]);
  const [activeTab, setActiveTab] = useState("info"); // Estado para manejar las pestañas
  const [tutorials, setTutorials] = useState({}); // Estado para almacenar los tutoriales
  const [loadingTutorials, setLoadingTutorials] = useState(true);
  const [selectedTutorial, setSelectedTutorial] = useState(null); // Estado para el tutorial seleccionado
  const [invertedTutorialsMap, setInvertedTutorialsMap] = useState({});

  useEffect(() => {
    retrievePersons();
    retrieveTutorials();
  }, [selectedTutorial]);

  const onChangeSearchNombre = (e) => {
    const searchTerm = e.target.value.toLowerCase();
    setSearchNombre(searchTerm);

    if (searchTerm === "") {
      setPersons(originalPersons);
    } else {
      const filteredPersons = originalPersons.filter((person) =>
        person.nombre.toLowerCase().includes(searchTerm)
      );
      setPersons(filteredPersons);
    }
  };

  const retrievePersons = async () => {
    try {
      const response = await AgendaDataService.getAll();

      // Asegurar que tutoriales contenga solo IDs
      const formattedPersons = response.data.map((person) => ({
        ...person,
        tutoriales: person.tutoriales.map(
          (tutorial) => tutorial.id || tutorial
        ), // Asegurar que sean IDs
      }));

      setPersons(formattedPersons);
      setOriginalPersons(formattedPersons);
    } catch (error) {
      console.error("Error retrieving persons:", error);
    }
  };

  const retrieveTutorials = async () => {
    try {
      const response = await TutorialDataService.getAll();

      const tutorialsMap = {};
      const invertedMap = {};

      response.data.forEach((tutorial) => {
        tutorialsMap[tutorial.id] = {
          id: tutorial.id,
          title: tutorial.title,
          description: tutorial.description,
          imageUrl: tutorial.imageUrl || null,
        };

        invertedMap[tutorial.title] = tutorial.id;
      });

      setTutorials(tutorialsMap);
      setInvertedTutorialsMap(invertedMap);
      setLoadingTutorials(false); // Marcar que los tutoriales ya se cargaron
    } catch (error) {
      console.error("Error retrieving tutorials:", error);
      setLoadingTutorials(false);
    }
  };

  const setActivePerson = (person, index) => {
    setCurrentPerson(person);
    setCurrentIndex(index);
    setActiveTab("info"); // Resetear la pestaña activa al cambiar de persona
    setSelectedTutorial(null); // Resetear la card de tutorial al cambiar de persona
  };

  const removeAllPersons = () => {
    AgendaDataService.deleteAll()
      .then(() => {
        setPersons([]);
        setOriginalPersons([]);
        setCurrentPerson(null);
        setCurrentIndex(-1);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const handlePersonDeleted = (deletedPersonId) => {
    setPersons((prevPersons) =>
      prevPersons.filter((person) => person.id !== deletedPersonId)
    );
    setOriginalPersons((prevOriginal) =>
      prevOriginal.filter((person) => person.id !== deletedPersonId)
    );
  };

  const handleSelectTutorial = (tutorialId) => {
    if (!tutorialId) {
      console.warn("ID de tutorial no válido:", tutorialId);
      return;
    }

    const tutorial = tutorials[tutorialId];

    setSelectedTutorial({
      title: tutorial.title || "Sin título",
      description: tutorial.description || "Sin descripción",
      imageUrl: tutorial.imageUrl || null,
    });
  };

  const progress = Math.min((originalPersons.length / MAX_PERSONS) * 100, 100);

  return (
    <div className="persons-container">
      <div className="search-container">
        <input
          type="text"
          className="search-bar"
          placeholder="Buscar por nombre..."
          value={searchNombre}
          onChange={onChangeSearchNombre}
        />

        <div className="progress-container">
          <div className="progress-bar" style={{ width: `${progress}%` }}>
            {progress}%
          </div>
        </div>
      </div>

      <div className="content">
        <div className="persons-list">
          <h2>Lista de Personas</h2>
          <ul className="list">
            {persons &&
              persons.map((person, index) => (
                <li
                  key={person.id}
                  className={`person-card ${
                    index === currentIndex ? "active" : ""
                  }`}
                  onClick={() => setActivePerson(person, index)}
                >
                  <p className="person-name">{person.nombre}</p>
                </li>
              ))}
          </ul>
          <div className="button-container">
            <button className="btn-danger" onClick={removeAllPersons}>
              Eliminar Todo
            </button>
          </div>
        </div>

        <div className="person-details">
          {currentPerson ? (
            <div className="details-card">
              <h2>Detalles de la Persona</h2>

              {/* Tabs para alternar entre Información y Tutoriales */}
              <div className="tabs">
                <button
                  className={`tab ${activeTab === "info" ? "active" : ""}`}
                  onClick={() => setActiveTab("info")}
                >
                  Información
                </button>
                <button
                  className={`tab ${activeTab === "tutorials" ? "active" : ""}`}
                  onClick={() => setActiveTab("tutorials")}
                >
                  Tutoriales
                </button>
              </div>

              {/* Contenido de la pestaña Información */}
              {activeTab === "info" && (
                <div className="info-tab">
                  <p>
                    <strong>Nombre:</strong> {currentPerson.nombre}
                  </p>
                  <p>
                    <strong>Apellidos:</strong> {currentPerson.apellidos}
                  </p>
                  <p>
                    <strong>Calle:</strong> {currentPerson.calle}
                  </p>
                  <p>
                    <strong>Código Postal:</strong> {currentPerson.codigoPostal}
                  </p>
                  <p>
                    <strong>Ciudad:</strong> {currentPerson.ciudad}
                  </p>
                  <p>
                    <strong>Fecha Nacimiento:</strong>{" "}
                    {currentPerson.fechaNacimiento}
                  </p>
                </div>
              )}

              {/* Contenido de la pestaña Tutoriales */}
              {activeTab === "tutorials" && (
                <div className="tutorials-tab">
                  {currentPerson.tutoriales &&
                  currentPerson.tutoriales.length > 0 ? (
                    <ul className="tutorials-list">
                      {currentPerson.tutoriales.map((tutorialTitle, index) => {
                        // Intentamos obtener el ID del tutorial basándonos en el título
                        const tutorialId = invertedTutorialsMap[tutorialTitle];
                        const tutorial = tutorials[tutorialId];

                        return (
                          <li
                            key={index}
                            className="tutorial-item"
                            onClick={() => handleSelectTutorial(tutorialId)}
                          >
                            {tutorial
                              ? tutorial.title
                              : "Tutorial no encontrado"}
                          </li>
                        );
                      })}
                    </ul>
                  ) : (
                    <p>No tiene tutoriales asignados</p>
                  )}
                </div>
              )}

              {user && (
                <div className="button-group">
                  <Link
                    to={`/agenda/${currentPerson.id}`}
                    className="btn-warning"
                  >
                    Editar
                  </Link>
                  <ButtonDelete
                    personId={currentPerson.id}
                    onDelete={handlePersonDeleted}
                  />
                </div>
              )}
            </div>
          ) : (
            <p className="select-message">
              Selecciona una persona para ver detalles...
            </p>
          )}
        </div>

        {selectedTutorial && selectedTutorial.title && (
          <div className="tutorial-details">
            <h2>{selectedTutorial.title}</h2>
            <p>{selectedTutorial.description}</p>
            {selectedTutorial.imageUrl && (
              <img
                src={selectedTutorial.imageUrl}
                alt={selectedTutorial.title}
                className="tutorial-image"
              />
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default PersonsList;
