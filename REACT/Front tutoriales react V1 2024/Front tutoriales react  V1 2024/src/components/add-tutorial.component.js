import React, { useState } from "react";  
import TutorialDataService from "../services/tutorial.service";

const AddTutorial = () => {
    // Definimos los estados para manejar los valores del formulario y el estado de envío
    const [id, setId] = useState(""); 
    const [title, setTitle] = useState(""); 
    const [description, setDescription] = useState(""); 
    const [published, setPublished] = useState(false); 
    const [submitted, setSubmitted] = useState(false);

    // Maneja el cambio de estado del checkbox
    const handleCheckboxChange = (event) => {
        setPublished(event.target.checked);
    };

    // Función para guardar el tutorial en la base de datos
    const saveTutorial = () => {
        // Creamos un objeto con los datos ingresados
        const data = {
            id,
            title,
            description,
            published
        };

        // Llamamos al servicio para crear el tutorial
        TutorialDataService.create(data)
            .then(response => {
                // Si la respuesta es exitosa, actualizamos los estados con los valores de la base de datos
                setId(response.data.id);
                setTitle(response.data.title);
                setDescription(response.data.description);
                setPublished(response.data.published);
                setSubmitted(true); // Cambiamos el estado a "enviado"
            })
            .catch(error => {
                console.error("Error adding tutorial:", error);
            });        
    };

    // Función para reiniciar el formulario y agregar un nuevo tutorial
    const newTutorial = () => {
        setId(""); 
        setTitle(""); 
        setDescription(""); 
        setPublished(false); 
        setSubmitted(false); 
    };

    return (
        <div className="submit-form">
            {/* Si el formulario ha sido enviado, mostramos el mensaje de éxito y un botón para agregar otro */}
            { submitted ? (
                <div>
                    <h4>Tutorial added successfully!</h4>
                    <button className="btn btn-success" onClick={newTutorial}>
                        Add
                    </button> {/* Botón para agregar un nuevo tutorial */}
                </div>
            ) : (
                // Si el formulario no ha sido enviado, mostramos los campos de entrada
                <div>
                    <div className="form-group">
                        <label htmlFor="id">Id</label>
                        <input type="text" className="form-control" id="id" name="id" required onChange={(e) => setId(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input type="text" className="form-control" id="title" name="title" required onChange={(e) => setTitle(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <input className="form-control" type="text" id="description" name="description" required onChange={(e) => setDescription(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="published">Published &nbsp;</label>
                        <input type="checkbox" id="published" name="published" onChange={handleCheckboxChange} />
                    </div>

                    {/* Botón para enviar los datos y guardar el tutorial */}
                    <div>
                        <button type="submit" className="btn btn-success" onClick={saveTutorial}>
                            Submit
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default AddTutorial;
