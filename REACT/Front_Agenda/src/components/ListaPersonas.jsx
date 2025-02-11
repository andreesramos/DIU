import React, { useEffect, useState } from 'react';
import { fetchData } from '../services/api';

function ListaPersonas() {
    const [personas, setPersonas] = useState([]);

    useEffect(() => {
        const getPersonas = async () => {
            try {
                const data = await fetchData();
                setPersonas(data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        getPersonas();
    }, []);

    return (
        <div>
            <h1>Lista de Personas</h1>
            <ul>
                {personas.map((persona, index) => (
                    <li key={index}>{persona.name}</li>
                ))}
            </ul>
        </div>
    );
}

export default ListaPersonas;