import React from "react";
import { Form, Button, Row, Col, FormGroup } from 'react-bootstrap';

class Formulario extends React.Component {

    constructor() {
        super();
        this.state = {
            artista: '',
            cancion: ''
        }
    }

    handleChange = event => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({ [name]: value });
    }

    handleSubmit = event => {
        event.preventDefault();
        fetch(`https://api.lyrics.ovh/v1/${artista}/${cancion}`, {
            method: 'POST',
            body: JSON.stringify({ artista: this.state.artista, cancion: this.state.cancion }),
        })
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error(response.statusText);
            }
        })
        .then(data => {
            this.props.buscarLyrics(data.lyrics);
        })
    }

    render(){
        return (
            <Form onSubmit={this.handleSubmit}>
                <Row>
                    <Col>
                        <FormGroup>
                            <Form.Label>Artista / Grupo</Form.Label>
                            <Form.Control placeholder="Introduce artista o grupo" type="text" name="artista" value={this.state.artista} onChange={this.handleChange} />
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup>
                            <Form.Label>Canción</Form.Label>
                            <Form.Control placeholder="Introduce canción" type="text" name="cancion" value={this.state.cancion} onChange={this.handleChange} />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <FormGroup>
                        <Button type="submit">Buscar</Button>
                    </FormGroup>
                </Row>
            </Form>
        )
    }
}

export default Formulario;