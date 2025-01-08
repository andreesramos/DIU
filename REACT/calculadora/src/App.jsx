import { useState } from 'react'
import './App.css'
import * as math from "mathjs";
import Calculadora from './components/Calculadora';

function App() {

  const [num, setNum] = useState(0);

  const cambiarTexto = () => {
    setNum(math.evaluate(num));
  }

  const borrar = () => {
    setNum(0);
  }

  return (
    <>
      <div>
        <Calculadora num={num} cambiarTexto={cambiarTexto} borrar={borrar}></Calculadora>
      </div>
    </>
  )
}

export default App
