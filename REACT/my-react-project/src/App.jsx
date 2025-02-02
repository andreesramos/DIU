import { useState } from 'react'
import './App.css'
import ButtonComponent from './components/ButtonComponent'
import HeaderComponent from './components/HeaderComponent'

function App() {
  // let number = 0;
  const [number, setNumber] = useState(0);
  const [myValue, setMyValue] = useState('');
  let myPlaceholder = "Escribe aqui";

  const [greetings, setGreetings] = useState("Bienvenidos a mi web");
  const links = {
    home: 'Home',
    blog: 'Blog',
    news: 'News',
    contact: 'Contact us'
  }

  const addOne = () => {
    // number++;
    setNumber(number + 1);
    console.log(number);
  }

  const sayHello = () => {
    console.log('Hello');
  }

  const handleChange = (e) => {
    console.log(e.target.value);
  }

  return (
    <>
      <HeaderComponent greetings={greetings} links={links}></HeaderComponent>
      
      <main className='main-content'>
        <h2 onClick={sayHello}>Hola a todos</h2>

        <h2 onClick={addOne}>Number: {number}</h2>

        <input value={myValue} placeholder={myPlaceholder} type="text" onChange={handleChange} />

        <br />
        <br />
        <ButtonComponent></ButtonComponent>
      </main>
      
    </>
  )
}

export default App
