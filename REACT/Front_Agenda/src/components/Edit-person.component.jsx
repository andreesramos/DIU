import React, { useState, useEffect } from "react";
import AgendaDataService from "../services/agenda.service";
import { useParams } from "react-router-dom";

const EditPerson = () => {
    const { id } = useParams();
    const [nombre, setNombre] = useState(""); 
    const [apellidos, setApellidos] = useState(""); 
    const [calle, setCalle] = useState("");
    const [codigoPostal, setCodigoPostal] = useState(0);
    const [ciudad, setCiudad] = useState("");
    const [fechaNacimiento, setFechaNacimiento] = useState("");
    const [tutoriales, setTutoriales] = useState([]);
    const [submitted, setSubmitted] = useState(false);

    useEffect(() => {
        getPerson(id);
    }, [id]);

    const getPerson = (id) => {
        AgendaDataService.get(id)
            .then(response => {
                setNombre(response.data.nombre);
                setApellidos(response.data.apellidos);
                setCalle(response.data.calle);
                setCodigoPostal(response.data.codigoPostal);
                setCiudad(response.data.ciudad);
                setFechaNacimiento(response.data.fechaNacimiento);
                //setTutoriales(response.data.tutoriales);
            })
            .catch(error => {
                console.error("Error fetching person:", error);
            });
    };

    /*const handleCheckboxChange = (event) => {
        setPublished(event.target.checked);
    };*/

    const updatePerson = () => {
        const data = {
            id,
            nombre,
            apellidos,
            calle,
            codigoPostal,
            ciudad,
            fechaNacimiento,
            //tutoriales
        };

        AgendaDataService.update(id, data)
            .then(response => {
                setSubmitted(true);
            })
            .catch(error => {
                console.error("Error updating person:", error);
            });
    };

    return (
        <div className="submit-form">
            {
                submitted ? (
                    <div>
                        <h4>Person updated successfully!</h4>
                    </div>
                ) : (
                    <div>
                    <div className="form-group">
                        <label htmlFor="id">Id</label>
                        <input type="text" className="form-control" id="id" name="id" value={id} required readOnly />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="nombre">Nombre</label>
                        <input type="text" className="form-control" id="nombre" name="nombre" value={nombre} required onChange={(e) => setNombre(e.target.value)} />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="apellidos">Apellidos</label>
                        <input className="form-control" type="text" id="apellidos" name="apellidos" value={apellidos} required onChange={(e) => setApellidos(e.target.value)} />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="calle">Calle</label>
                        <input className="form-control" type="text" id="calle" name="calle" value={calle} required onChange={(e) => setCalle(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="codigoPostal">Codigo Postal</label>
                        <input className="form-control" type="text" id="codigoPostal" name="codigoPostal" value={codigoPostal} required onChange={(e) => setCodigoPostal(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="ciudad">Ciudad</label>
                        <input className="form-control" type="text" id="ciudad" name="ciudad" value={ciudad} required onChange={(e) => setCiudad(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="fechaNacimiento">Fecha de Nacimiento</label>
                        <input className="form-control" type="text" id="fechaNacimiento" name="fechaNacimiento" value={fechaNacimiento} required onChange={(e) => setFechaNacimiento(e.target.value)} />
                    </div>

                    {/*<div className="form-group">
                        <label htmlFor="tutoriales">Tutoriales</label>
                        <input className="form-control" type="text" id="tutoriales" name="tutoriales" value={tutoriales} required onChange={(e) => setTutoriales(e.target.value)} />
                    </div>*/}
    
                    <div>
                        <button type="submit" className="btn btn-success" onClick={updatePerson}>
                            Update
                        </button>
                    </div>
                </div>
                )
            }
            
        </div>
    );
};

export default EditPerson;
