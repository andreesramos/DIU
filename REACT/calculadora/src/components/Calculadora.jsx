function Calculadora(props){

    const{num, cambiarTexto, borrar} = props;

    return (
        <div>
            <div>
                <h1>CALCULADORA</h1>
            </div>
            <div>
                <input type="text" value={num}/>
            </div>
            <div>
                <button onClick={borrar}>AC</button>
                <button onClick={cambiarTexto}>+/-</button>
                <button onClick={cambiarTexto}>%</button>
                <button onClick={cambiarTexto}>/</button>
            </div>
            <div>
                <button onClick={cambiarTexto}>7</button>
                <button onClick={cambiarTexto}>8</button>
                <button onClick={cambiarTexto}>9</button>
                <button onClick={cambiarTexto}>x</button>
            </div>
            <div>
                <button onClick={cambiarTexto}>4</button>
                <button onClick={cambiarTexto}>5</button>
                <button onClick={cambiarTexto}>6</button>
                <button onClick={cambiarTexto}>-</button>
            </div>
            <div>
                <button onClick={cambiarTexto}>1</button>
                <button onClick={cambiarTexto}>2</button>
                <button onClick={cambiarTexto}>3</button>
                <button onClick={cambiarTexto}>+</button>
            </div>
            <div>
                <button onClick={cambiarTexto}>0</button>
                <button onClick={cambiarTexto}>.</button>
                <button onClick={cambiarTexto}>=</button>
            </div>
        </div>
    )
}

export default Calculadora;