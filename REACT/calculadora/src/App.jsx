import { useState } from 'react'
import './App.css'
import * as math from "mathjs";
import Calculadora from './components/Calculadora';

function App() {

  const [num, setNum] = useState(0);
  const [igual, setIgual] = useState();

  const cambiarTexto = (escrito) => {
    if (igual && !isNaN(escrito)){
      setNum(escrito)
    }else{
      num != 0 ? setNum(num + escrito) : setNum(escrito)
    }
    setIgual(false)
  }
    
  const borrar = () => {
    setNum(0);
    setIgual(false)
  }

  const calcular = () => {
    setNum(math.evaluate(num))
    setIgual(true)
  }

  const negativo = () => {
    setNum(num * -1)
  }

  return (
    <>
      <div className='todo'>
        <Calculadora num={num} igual={igual} cambiarTexto={cambiarTexto} borrar={borrar} calcular={calcular} negativo={negativo}></Calculadora>
      </div>
    </>
  )
}

export default App
