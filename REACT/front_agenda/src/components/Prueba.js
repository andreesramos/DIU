import { useState, useEffect } from "react";

function Prueba() {
    const [agenda, setAgenda] = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchAgenda = async () => {
        try {
            const response = await fetch(`http://localhost:8099/api/v1/agenda`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Fetch error:', error);
            return [];
        }
    };

    useEffect(() => {
        const getAgenda = async () => {
            const data = await fetchAgenda();
            setAgenda(data);
            setLoading(false);
        };
        getAgenda();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (!agenda.length) {
        return <div>Error loading agenda</div>;
    }

    return (
        <div>
            <h1>Agenda</h1>
            <pre>{JSON.stringify(agenda, null, 2)}</pre>
        </div>
    );
}

export default Prueba;