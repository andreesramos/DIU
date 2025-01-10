import "./Calculadora.css"

function Calculadora(props){

    const{num, igual, cambiarTexto, borrar, calcular, negativo} = props;

    const handleClick = (event) => {
        const texto = event.target.innerText;
        cambiarTexto(texto);
    }

    return (
        <div>
            <div>
                <input type="text" className="barra" value={num}/>
            </div>
            <div>
                <button className="gris" onClick={borrar}>AC</button>
                <button className="gris" onClick={negativo}>+/-</button>
                <button className="gris" onClick={handleClick}>%</button>
                <button className="naranja" onClick={handleClick}>/</button>
            </div>
            <div>
                <button onClick={handleClick}>7</button>
                <button onClick={handleClick}>8</button>
                <button onClick={handleClick}>9</button>
                <button className="naranja" onClick={handleClick}>*</button>
            </div>
            <div>
                <button onClick={handleClick}>4</button>
                <button onClick={handleClick}>5</button>
                <button onClick={handleClick}>6</button>
                <button className="naranja" onClick={handleClick}>-</button>
            </div>
            <div>
                <button onClick={handleClick}>1</button>
                <button onClick={handleClick}>2</button>
                <button onClick={handleClick}>3</button>
                <button className="naranja" onClick={handleClick}>+</button>
            </div>
            <div>
                <button className="cero" onClick={handleClick}>0</button>
                <button onClick={handleClick}>.</button>
                <button className="naranja" onClick={calcular}>=</button>
            </div>
        </div>
    )
}

export default Calculadora;