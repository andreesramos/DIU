import React, { useEffect, useState } from "react";
import AgendaDataService from "../services/agenda.service";
import TutorialDataService from "../services/tutorial.service";
import { useNavigate } from "react-router-dom";
import { usePersons } from "../context/PersonContext";
import "../styles/AddPerson.css";

const AddPerson = () => {
  const navigate = useNavigate();
  const { retrievePersons } = usePersons();

  // Definimos los estados para manejar los valores del formulario y el estado de envío
  const [nombre, setNombre] = useState("");
  const [apellidos, setApellidos] = useState("");
  const [calle, setCalle] = useState("");
  const [codigoPostal, setCodigoPostal] = useState(0);
  const [ciudad, setCiudad] = useState("");
  const [fechaNacimiento, setFechaNacimiento] = useState("");
  const [tutoriales, setTutoriales] = useState([]);
  const [tutorialesDisponibles, setTutorialesDisponibles] = useState([]);
  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    // Cargar tutoriales publicados desde la API
    const fetchTutoriales = async () => {
      try {
        const response = await TutorialDataService.getPublished();
        setTutorialesDisponibles(
          Array.isArray(response.data) ? response.data : []
        );
      } catch (error) {
        console.error("Error obteniendo tutoriales:", error);
        setTutorialesDisponibles([]);
      }
    };

    fetchTutoriales();
  }, []);

  // Función para guardar la persona en la base de datos
  const savePerson = async () => {
    setError("");

    if (
      !nombre ||
      !apellidos ||
      !calle ||
      !codigoPostal ||
      !ciudad ||
      !fechaNacimiento
    ) {
      setError("Todos los campos son obligatorios.");
      return;
    }

    // Creamos un objeto con los datos ingresados
    const data = {
      nombre,
      apellidos,
      calle,
      codigoPostal: parseInt(codigoPostal, 10),
      ciudad,
      fechaNacimiento,
      tutoriales,
    };

    try {
      await AgendaDataService.create(data);
      await retrievePersons();
      navigate("/agenda");
    } catch (error) {
      console.error("Error añadiendo persona:", error);
      setError("No se pudo guardar la persona.");
    }
  };

  return (
    <div className="add-person-container">
      <div className="add-person-form">
        <h2>Añadir Persona</h2>
        {error && <p className="error-message">{error}</p>}

        <label htmlFor="nombre">Nombre</label>
        <input
          type="text"
          id="nombre"
          required
          onChange={(e) => setNombre(e.target.value)}
        />

        <label htmlFor="apellidos">Apellidos</label>
        <input
          type="text"
          id="apellidos"
          required
          onChange={(e) => setApellidos(e.target.value)}
        />

        <label htmlFor="calle">Calle</label>
        <input
          type="text"
          id="calle"
          required
          onChange={(e) => setCalle(e.target.value)}
        />

        <label htmlFor="codigoPostal">Código Postal</label>
        <input
          type="number"
          id="codigoPostal"
          min="1"
          required
          onChange={(e) => {
            const value = parseInt(e.target.value, 10);
            setCodigoPostal(value < 1 || isNaN(value) ? 1 : value);
          }}
        />

        <label htmlFor="ciudad">Ciudad</label>
        <input
          type="text"
          id="ciudad"
          required
          onChange={(e) => setCiudad(e.target.value)}
        />

        <label htmlFor="fechaNacimiento">Fecha de Nacimiento</label>
        <input
          type="date"
          id="fechaNacimiento"
          required
          onChange={(e) => setFechaNacimiento(e.target.value)}
        />

        <label htmlFor="tutoriales">Tutoriales</label>
        <select
          id="tutoriales"
          multiple
          value={tutoriales}
          onChange={(e) =>
            setTutoriales(
              Array.from(e.target.selectedOptions, (option) => option.value)
            )
          }
        >
          {Array.isArray(tutorialesDisponibles) &&
          tutorialesDisponibles.length > 0 ? (
            tutorialesDisponibles.map((tutorial) => (
              <option key={tutorial.id} value={String(tutorial.id)}>
                {tutorial.title}
              </option>
            ))
          ) : (
            <option disabled>No hay tutoriales disponibles</option>
          )}
        </select>

        <div className="button-group">
          <button
            type="submit"
            className="btn-success"
            onClick={savePerson}
            disabled={
              !nombre ||
              !apellidos ||
              !calle ||
              !codigoPostal ||
              !ciudad ||
              !fechaNacimiento ||
              codigoPostal < 1
            }
          >
            Guardar
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddPerson;
