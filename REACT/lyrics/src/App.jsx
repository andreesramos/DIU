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

  /*componentDidMount() {
    fetch('https://api.lyrics.ovh/v1/{artista}/{cancion}')
    .then(response => {
      if(response.ok){
        return response.json();
      }else{
        throw new Error(response.statusText);
      }
    })
    .then(data => {
      this.setState({data: data});
    })
  }*/

  /*const buscarLyrics = async () => {
    const response = await fetch(`https://api.lyrics.ovh/v1/${artista}/${cancion}`, {
      method: 'POST',
      body: JSON.stringify({expr: num}),
    });
    const data = await response.json();
    if(response.ok){
      setLetra(data.lyrics);
    }else{
      throw new Error(response.statusText);
    }

  };*/

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
