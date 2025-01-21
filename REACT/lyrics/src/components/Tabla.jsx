import React from "react";
import { Table } from 'react-bootstrap';


class Tabla extends React.Component {

    constructor() {
        super();
    }
    
    renderData(data) {
        return (
            <tr>
                <td>{data.artist}</td>
                <td>{data.song}</td>
                <td>{data.lyrics}</td>
            </tr>
        )
    }

    render() {
        return (
            <Table responsive striped bordered hover size="sm">
                <thead>
                        <tr>
                            <th>Artista/Grupo</th>
                            <th>Cancion</th>
                            <th>Letra</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.props.data.map(this.renderData)}
                    </tbody>
            </Table>)
    }
}

export default Tabla;