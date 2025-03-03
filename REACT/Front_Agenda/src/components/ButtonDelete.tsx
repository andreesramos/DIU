import React, { useState } from "react";
import "../styles/ButtonDelete.css";
import { usePersons } from "../context/PersonContext";

const ButtonDelete = ({ personId, onDelete }) => {  
    const { deletePerson } = usePersons();
    const [isDeleting, setIsDeleting] = useState(false);

    const handleClick = () => {
        if (!personId) return;  

        setIsDeleting(true);

        // Esperar antes de eliminar, para que la animación se complete
        setTimeout(async () => {
            await deletePerson(personId);  // Eliminamos después de la animación
            onDelete(personId);
            setIsDeleting(false);
        }, 2500);
    };

    return (
        <button 
            onClick={handleClick} 
            className={isDeleting ? "deleting" : ""}
            disabled={isDeleting} // Evita múltiples clics durante la animación
        >
            <span className="button-text">
                {isDeleting ? "Deleting..." : "Delete"}
            </span>
            <span className="animation">
                <span className="balls"></span>
                <span className="lid"></span>
                <span className="can">
                    <span className="filler"></span>
                </span>
            </span>
        </button>
    );
}

export default ButtonDelete;
