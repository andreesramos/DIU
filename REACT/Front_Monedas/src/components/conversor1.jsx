import React, { useState, useEffect } from "react";
import { getAllProducts } from "../services/api.service";
import "../styles/Conversor1.css";
import ProductTable from "./ProductTable";

export default function Conversor1() {
  // Estados
  const [products, setProducts] = useState([]); // Lista de productos
  const [selectedProduct, setSelectedProduct] = useState(null); // Producto de origen
  const [selectedProduct2, setSelectedProduct2] = useState(null); // Producto de destino
  const [inputValue, setInputValue] = useState(""); // Valor de entrada
  const [result, setResult] = useState(null); // Resultado de conversión

  // Obtiene los productos al montar el componente
  useEffect(() => {
    getAllProducts().then((response) => setProducts(response.data));
  }, []);

  // Maneja la conversión de moneda
  const handleConvert = () => {
    const value = parseFloat(inputValue);

    if (!isNaN(value) && selectedProduct && selectedProduct2) {
      const conversion = (value * selectedProduct2.price) / selectedProduct.price;
      setResult(conversion.toFixed(2));
    } else {
      setResult("Ingrese un valor válido");
    }
  };

  return (
    <div className="conversor-container">
      <h1>Conversor de monedas</h1>

      {/* Input para ingresar el valor a convertir */}
      <div className="input-container">
        <label htmlFor="value">Valor a convertir:</label>
        <input
          type="number"
          id="value"
          value={inputValue}
          min="0"
          onChange={(e) => setInputValue(e.target.value)}
        />
      </div>

      <div className="tables-container">
        {/* Tabla de Moneda de Origen */}
        <ProductTable
          title="Moneda de origen"
          products={products}
          onSelect={setSelectedProduct}
          selected={selectedProduct}
        />

        {/* Tabla de Moneda de Destino */}
        <ProductTable
          title="Moneda de destino"
          products={products}
          onSelect={setSelectedProduct2}
          selected={selectedProduct2}
        />
      </div>

      {/* Botón para realizar la conversión */}
      <div>
        <button
          onClick={handleConvert}
          disabled={!selectedProduct || !selectedProduct2 || inputValue === ""}
        >
          Convertir
        </button>
      </div>

      {/* Muestra el resultado de la conversión */}
      {result !== null && (
        <div className="result-container">
          <p>Resultado: {result}</p>
        </div>
      )}
    </div>
  );
}