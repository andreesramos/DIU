import React from "react";
import { Table } from 'react-bootstrap';
import "./Tabla.css";


class Tabla extends React.Component {

    constructor() {
        super();
    }
    
    renderData(data, index) {
        return (
            <tr key={index}>
                <td>{data.artista}</td>
                <td>{data.cancion}</td>
                <td>{data.letra}</td>
            </tr>
        )
    }

    render() {
        const { data } = this.props;
    
        return (
            <Table responsive striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>Artista/Grupo</th>
                        <th>Canción</th>
                        <th>Letra</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map(this.renderData)}
                </tbody>
            </Table>
        );
    }
    
}

export default Tabla;