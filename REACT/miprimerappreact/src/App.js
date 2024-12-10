import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import HelloComponent from './components/HelloComponent';
import InputComponent from './components/InputComponent';

class App extends Component{
  constructor(){
    super()
    this.state={
      name: 'Andres'
    }
  }

  changeName=(event)=>{
    this.setState({
      name: event.target.value
    })
  }

  render(){
    return(
      <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <HelloComponent nombre={this.state.name}></HelloComponent>
        <InputComponent nombre={this.state.name} cambiarNombre={this.changeName}></InputComponent>
      </header>
    </div>
    )
  }
}


/*function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <HelloComponent></HelloComponent>
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}*/

export default App;
