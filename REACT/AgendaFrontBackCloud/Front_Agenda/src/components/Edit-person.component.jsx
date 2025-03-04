import React, { useState, useEffect } from "react";
import AgendaDataService from "../services/agenda.service";
import TutorialDataService from "../services/tutorial.service";
import { usePersons } from "../context/PersonContext";
import { useNavigate, useParams } from "react-router-dom";
import "../styles/AddPerson.css";

const EditPerson = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { retrievePersons } = usePersons();

  const [nombre, setNombre] = useState("");
  const [apellidos, setApellidos] = useState("");
  const [calle, setCalle] = useState("");
  const [codigoPostal, setCodigoPostal] = useState(0);
  const [ciudad, setCiudad] = useState("");
  const [fechaNacimiento, setFechaNacimiento] = useState("");
  const [tutoriales, setTutoriales] = useState([]);
  const [tutorialesDisponibles, setTutorialesDisponibles] = useState([]);
  //const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    getPerson(id);
    fetchTutoriales();
  }, [id]);

  const getPerson = async (id) => {
    try {
      const response = await AgendaDataService.get(id);
      setNombre(response.data.nombre);
      setApellidos(response.data.apellidos);
      setCalle(response.data.calle);
      setCodigoPostal(response.data.codigoPostal);
      setCiudad(response.data.ciudad);
      setFechaNacimiento(response.data.fechaNacimiento);
      setTutoriales(response.data.tutoriales || []);
    } catch (error) {
      console.error("Error obteniendo persona:", error);
      setError("No se pudo cargar la persona.");
    }
  };

  const fetchTutoriales = async () => {
    try {
      const response = await TutorialDataService.getPublished();
      setTutorialesDisponibles(
        Array.isArray(response.data) ? response.data : []
      );
    } catch (error) {
      console.error("Error obteniendo tutoriales:", error);
      setError("No se pudo cargar la lista de tutoriales.");
    }
  };

  const updatePerson = async () => {
    setError("");
    const updatedPerson = {
      id,
      nombre,
      apellidos,
      calle,
      codigoPostal: parseInt(codigoPostal, 10),
      ciudad,
      fechaNacimiento,
      tutoriales,
    };

    try {
      await AgendaDataService.update(id, updatedPerson);
      await retrievePersons();
      navigate("/agenda");
    } catch (error) {
      console.error("Error updating person:", error);
      setError("No se pudo actualizar la persona.");
    }
  };

  return (
    <div className="add-person-container">
      <div className="add-person-form">
        <h2>Editar Persona</h2>
        {error && <p className="error-message">{error}</p>}

        <label htmlFor="nombre">Nombre</label>
        <input
          type="text"
          id="nombre"
          name="nombre"
          value={nombre}
          required
          onChange={(e) => setNombre(e.target.value)}
        />

        <label htmlFor="apellidos">Apellidos</label>
        <input
          type="text"
          id="apellidos"
          name="apellidos"
          value={apellidos}
          required
          onChange={(e) => setApellidos(e.target.value)}
        />

        <label htmlFor="calle">Calle</label>
        <input
          type="text"
          id="calle"
          name="calle"
          value={calle}
          required
          onChange={(e) => setCalle(e.target.value)}
        />

        <label htmlFor="codigoPostal">Codigo Postal</label>
        <input
          type="number"
          id="codigoPostal"
          name="codigoPostal"
          value={codigoPostal}
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
          name="ciudad"
          value={ciudad}
          required
          onChange={(e) => setCiudad(e.target.value)}
        />

        <label htmlFor="fechaNacimiento">Fecha de Nacimiento</label>
        <input
          type="date"
          id="fechaNacimiento"
          name="fechaNacimiento"
          value={fechaNacimiento}
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
              <option key={tutorial.id} value={tutorial.id}>
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
            className="btn btn-success"
            onClick={updatePerson}
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
            Actualizar
          </button>
        </div>
      </div>
    </div>
  );
};

export default EditPerson;
