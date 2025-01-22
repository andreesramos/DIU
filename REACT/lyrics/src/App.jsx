import './App.css'
import React from "react";
import { Row, Col, Container } from 'react-bootstrap';
import Formulario from './components/Formulario';
import Tabla from './components/Tabla';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      data: [],
      artista: '',
      cancion: '',
      letra: ''
    }
  }
  
  passParams= (data) => {
    let dataNew = this.state.data; 
    dataNew.push(data) 
    this.setState({
      data: dataNew
    });
  }

  componentDidMount() {
    fetch('https://api.lyrics.ovh/v1/${artista}/${cancion}')
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al cargar los datos iniciales.");
            }
            return response.json();
        })
        .then(data => {
          this.setState({ data: [data] });
        });
}

  render(){
    return (
      <Container>
        <Row>
          <Col>
            <Formulario passParams={this.passParams} />
          </Col>
        </Row>
        <Row>
          <Col>
            <Tabla data={this.state.data}/>
          </Col>
        </Row>
      </Container>
    )
  }
}

export default App
