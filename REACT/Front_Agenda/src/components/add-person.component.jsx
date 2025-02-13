import React, { useState } from "react";  
import AgendaDataService from "../services/agenda.service";

const AddPerson = () => {
    // Definimos los estados para manejar los valores del formulario y el estado de envío
    const [id, setId] = useState(""); 
    const [nombre, setNombre] = useState(""); 
    const [apellidos, setApellidos] = useState(""); 
    const [calle, setCalle] = useState("");
    const [codigoPostal, setCodigoPostal] = useState(0);
    const [ciudad, setCiudad] = useState("");
    const [fechaNacimiento, setFechaNacimiento] = useState("");
    const [submitted, setSubmitted] = useState(false);

    // Maneja el cambio de estado del checkbox
    // const handleCheckboxChange = (event) => {
    //     setCalle(event.target.checked);
    // };

    // Función para guardar la persona en la base de datos
    const savePerson = () => {
        // Creamos un objeto con los datos ingresados
        const data = {
            id,
            nombre: nombre,
            apellidos: apellidos,
            calle: calle,
            codigoPostal: codigoPostal,
            ciudad: ciudad,
            fechaNacimiento: fechaNacimiento
        };

        // Llamamos al servicio para crear la persona
        AgendaDataService.create(data)
            .then(response => {
                // Si la respuesta es exitosa, actualizamos los estados con los valores de la base de datos
                setId(response.data.id);
                setNombre(response.data.nombre);
                setApellidos(response.data.apellidos);
                setCalle(response.data.calle);
                setCodigoPostal(response.data.codigoPostal);
                setCiudad(response.data.ciudad);
                setFechaNacimiento(response.data.fechaNacimiento);
                setSubmitted(true); // Cambiamos el estado a "enviado"
            })
            .catch(error => {
                console.error("Error adding person:", error);
            });        
    };

    // Función para reiniciar el formulario y agregar una nueva persona
    const newPerson = () => {
        setId(""); 
        setNombre(""); 
        setApellidos(""); 
        setCalle("");
        setCodigoPostal(0);
        setCiudad("");
        setFechaNacimiento(""); 
        setSubmitted(false); 
    };

    return (
        <div className="submit-form">
            {/* Si el formulario ha sido enviado, mostramos el mensaje de éxito y un botón para agregar otro */}
            { submitted ? (
                <div>
                    <h4>Person added successfully!</h4>
                    <button className="btn btn-success" onClick={newPerson}>
                        Add
                    </button> {/* Botón para agregar una nueva persona */}
                </div>
            ) : (
                // Si el formulario no ha sido enviado, mostramos los campos de entrada
                <div>
                    <div className="form-group">
                        <label htmlFor="id">Id</label>
                        <input type="text" className="form-control" id="id" name="id" required onChange={(e) => setId(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="title">Nombre</label>
                        <input type="text" className="form-control" id="title" name="title" required onChange={(e) => setNombre(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="description">Apellidos</label>
                        <input className="form-control" type="text" id="description" name="description" required onChange={(e) => setApellidos(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="calle">Calle</label>
                        <input className="form-control" type="text" id="calle" name="calle" required onChange={(e) => setCalle(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="codigoPostal">Codigo Postal</label>
                        <input className="form-control" type="text" id="codigoPostal" name="codigoPostal" required onChange={(e) => setCodigoPostal(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="ciudad">Ciudad</label>
                        <input className="form-control" type="text" id="ciudad" name="ciudad" required onChange={(e) => setCiudad(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="fechaNacimiento">Fecha de Nacimiento</label>
                        <input className="form-control" type="text" id="fechaNacimiento" name="fechaNacimiento" required onChange={(e) => setFechaNacimiento(e.target.value)} />
                    </div>

                    {/* Botón para enviar los datos y guardar la persona */}
                    <div>
                        <button type="submit" className="btn btn-success" onClick={savePerson}>
                            Submit
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default AddPerson;
