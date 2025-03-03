import { createContext, useContext, useState, useEffect } from "react";
import AgendaDataService from "../services/agenda.service";
import TutorialDataService from "../services/tutorial.service";

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

    const retrievePersons = async () => {
        try {
            const personsResponse = await AgendaDataService.getAll();
            const tutorialResponse = await TutorialDataService.getAll(); // Obtener todos los tutoriales
    
            // Crear un diccionario (mapa) para buscar títulos de tutoriales por ID
            const tutorialsMap = tutorialResponse.data.reduce((map, tutorial) => {
                map[tutorial.id] = tutorial.title; // Cambiado de "nombre" a "title"
                return map;
            }, {});
    
            // Reemplazar los IDs de los tutoriales con sus títulos en cada persona
            const personsWithTutorialTitles = personsResponse.data.map(person => ({
                ...person,
                tutoriales: person.tutoriales.map(tutorialId => tutorialsMap[tutorialId] || "Tutorial Desconocido")
            }));
    
            setPersons(personsWithTutorialTitles);
        } catch (error) {
            console.error("Error retrieving persons or tutorials:", error);
        }
    };
    

    // Eliminar una persona
    const deletePerson = async (id) => { 
        if (!id) return;
    
        try {
            await AgendaDataService.delete(id);
            setPersons(persons.filter(person => person.id !== id));  // Filtra la persona eliminada
            if (currentPerson?.id === id) {
                setCurrentPerson(null);
                setCurrentIndex(-1);
            }
        } catch (error) {
            console.error("Error deleting person:", error);
        }
    };
    

    return (
        <PersonContext.Provider value={{
            persons,
            setPersons,
            currentPerson,
            setCurrentPerson,
            currentIndex,
            setCurrentIndex,
            deletePerson,
            retrievePersons
        }}>
            {children}
        </PersonContext.Provider>
    );
};
