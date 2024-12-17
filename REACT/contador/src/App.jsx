import { Component } from 'react'
import './App.css'
import Contador from './components/Contador'

class App extends Component{
  constructor(){
    super()
    this.state={
      num: 0
    }
  }

  incrementar = () => {
    this.setState((prevState) => ({
      num: prevState.num + 1,
    }));
  };

  decrementar=() => {
    this.setState((prevState) => ({
      num: prevState.num -1,
    }));
  };

  resetear=() => {
    this.setState({
      num: 0
    })
  }

  render(){
    return (
      <>
        <div>
          <Contador num={this.state.num} incrementar={this.incrementar} decrementar={this.decrementar} resetear={this.resetear}></Contador>
        </div>
      </>
    )
  }
}


/*function App() {

  const [num, setNum] = useState(0);

  const incrementar = () => {
    setNum(num+1);
  }

  const decrementar = () => {
    setNum(num-1);
  }

  const resetear = () => {
    setNum(0);
  }

  return (
    <>
      <div>
        <Contador num={num} incrementar={incrementar} decrementar={decrementar} resetear={resetear}></Contador>
      </div>
    </>
  )
}*/

export default App
