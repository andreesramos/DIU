import { createContext, useContext, useState, useEffect } from "react";
import AgendaDataService from "../services/agenda.service";

// Crear el contexto
const PersonContext = createContext();

// Hook personalizado para acceder fácilmente al contexto
export const usePersons = () => useContext(PersonContext);

// Proveedor del contexto
export const PersonProvider = ({ children }) => {
    const [persons, setPersons] = useState([]);
    const [currentPerson, setCurrentPerson] = useState(null);
    const [currentIndex, setCurrentIndex] = useState(-1);

    // Cargar lista de personas al inicio
    useEffect(() => {
        retrievePersons();
    }, []);

    // Obtener todas las personas
    const retrievePersons = async () => {
        try {
            const response = await AgendaDataService.getAll();
            setPersons(response.data);
        } catch (error) {
            console.error("Error retrieving persons:", error);
        }
    };

    // Eliminar una persona
    const deletePerson = async () => {
        if (!currentPerson) return;

        try {
            await AgendaDataService.delete(currentPerson.id);
            setPersons(persons.filter(person => person.id !== currentPerson.id)); // Filtrar la persona eliminada
            setCurrentPerson(null);
            setCurrentIndex(-1);
        } catch (error) {
            console.error("Error deleting person:", error);
        }
    };

    return (
        <PersonContext.Provider value={{
            persons,
            currentPerson,
            setCurrentPerson,
            setCurrentIndex,
            deletePerson
        }}>
            {children}
        </PersonContext.Provider>
    );
};
