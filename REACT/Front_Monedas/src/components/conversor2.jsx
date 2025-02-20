import { useState, useEffect } from "react";
import React from "react";
import { getAllProducts } from "../services/api.service";
import "../styles/Conversor2.css";
import ProductTable from "./ProductTable";

export default function Conversor2() {
  // Estados
  const [products, setProducts] = useState([]); // Lista de productos
  const [selectedProduct, setSelectedProduct] = useState(null); // Producto de origen seleccionado
  const [eurValue, setEurValue] = useState(""); // Valor ingresado en euros
  const [currencyValue, setCurrencyValue] = useState(""); // Valor ingresado en otra moneda

  // Obtiene los productos al montar el componente
  useEffect(() => {
    getAllProducts().then((response) => setProducts(response.data));
  }, []);

  // Función para manejar la conversión de moneda
  const handleConvert = () => {
    if (!selectedProduct) return; // No hace nada si no hay moneda seleccionada

    if (selectedProduct.name.toLowerCase() === "euros") {
      setCurrencyValue(eurValue);
      return;
    }

    if (eurValue) {
      setCurrencyValue((parseFloat(eurValue) * selectedProduct.price).toFixed(2));
    } else if (currencyValue) {
      setEurValue((parseFloat(currencyValue) / selectedProduct.price).toFixed(2));
    }
  };

  // Maneja cambios en el input de euros
  const handleEurChange = (e) => {
    const value = e.target.value;
    if (value === "" || parseFloat(value) >= 0) { // Evita valores negativos
      setEurValue(value);
      setCurrencyValue(""); // Borra el valor en la otra moneda al ingresar en euros
    }
  };

  // Maneja cambios en el input de otra moneda
  const handleCurrencyChange = (e) => {
    const value = e.target.value;
    if (value === "" || parseFloat(value) >= 0) { // Evita valores negativos
      setCurrencyValue(value);
      setEurValue(""); // Borra el valor en euros al ingresar en otra moneda
    }
  };

  return (
    <div className="conversor-container">
      <h1>Conversor de monedas</h1>

      {/* Tabla para seleccionar la moneda de origen */}
      <div className="tables-container">
        <ProductTable
          products={products}
          onSelect={setSelectedProduct}
          selected={selectedProduct}
        />
      </div>

      {/* Inputs para ingresar valores */}
      <div className="input-container">
        <div className="input-group">
          <label htmlFor="eur">EUR:</label>
          <input
            type="number"
            id="eur"
            value={eurValue}
            min="0"
            onChange={handleEurChange} // Actualiza el estado al escribir en el input
          />
        </div>

        <div className="input-group">
          <label htmlFor="currency">
            {selectedProduct ? selectedProduct.name : "Otra moneda"}:
          </label>
          <input
            type="number"
            id="currency"
            value={currencyValue}
            min="0"
            onChange={handleCurrencyChange} // Actualiza el estado al escribir en el input
          />
        </div>
      </div>

      {/* Botón de conversión (deshabilitado si falta información) */}
      <button onClick={handleConvert} disabled={!selectedProduct || (!eurValue && !currencyValue)}>
        Convertir
      </button>
    </div>
  );
}