import { useState, useEffect } from "react";
import React from "react";
import { findAll, updateCurrency } from "../services/conversor.service";
import "../styles/Conversor.css";
import ListaMonedas from "./ListaMonedas";
import ProgressBar from "./ProgressBar";

export default function Conversor() {
    const [coins, setCoins] = useState([]); 
    const [selectedCoin, setSelectedCoin] = useState(null); 
    const [euro, setEuro] = useState(""); 
    const [anyCoin, setAnyCoin] = useState("");

  useEffect(() => {
    findAll().then((response) => setCoins(response.data));
  }, []);


  const handleEurChange = (e) => {
    const value = e.target.value;
    setEuro(value);
    setAnyCoin((parseFloat(value) * selectedCoin.price).toFixed(2));
  };

  
  const handleCurrencyChange = (e) => {
    const value = e.target.value;
    if(value > selectedCoin.stock){
        setAnyCoin(0)
        setEuro(0)
    }else{
        setAnyCoin(value);
        setEuro((parseFloat(value) / selectedCoin.price).toFixed(2));
    }
    
    
  };

  const refreshList = () => {
    findAll().then((response) => setCoins(response.data));
  }

  /*const updateStock = () => {
    setId(selectedCoin.id);
    setStock(selectedCoin.stock);
    setName(selectedCoin.name);
    setBrand(selectedCoin.brand);
    setPrice(selectedCoin.price);
    setActive(selectedCoin.active);

    const data = {
        id,
        stock,
        name,
        brand,
        price,
        active,
    };

    updateCurrency(id, data)
        .then(() => {
            setSelectedCoin(data);
        })
        .catch((error) => {
            console.error("Error actualizando stock:", error);
        });
    refreshList();
  };*/

  const incrementarStock = () => {
    selectedCoin.stock = selectedCoin.stock +10;
    //updateStock();
    refreshList();
  }

  return (
    <div className="conversor-container">
      <h2>Conversor de monedas</h2>

      <div className="tables-container">
        <ListaMonedas
          coins={coins}
          onSelect={setSelectedCoin}
          selected={selectedCoin}
        />
      </div>

      {selectedCoin ? (
        <div className="container">
            <div className="input-container">
                <div className="input-group">
                    <label htmlFor="cantidad">Cantidad: </label>
                    <input
                        type="number"
                        id="cantidad"
                        value={anyCoin}
                        min="0"
                        step="0.01"
                        onChange={handleCurrencyChange}
                        disabled={!selectedCoin.active}
                    />
                </div>

                <div className="input-group">
                    <label htmlFor="euros">Equivalencia en euros: </label>
                    <input
                        type="number"
                        id="euros"
                        value={euro}
                        min="0"
                        step="0.01"
                        onChange={handleEurChange}
                        disabled={!selectedCoin.active}
                    />
                </div>
            </div>

            <div>
                <ProgressBar value={selectedCoin.stock} max="100" />
            </div>

            <div>
                {selectedCoin.stock < 100 ? (
                    <label className="stockNoLleno" htmlFor="stockNoLleno">Reserva no llena</label>
                ) : (
                    <label className="stockLleno" htmlFor="stockLleno">Reserva llena</label>
                )}
            </div>

            <div>
                <button onClick={incrementarStock} disabled={!selectedCoin.active || selectedCoin.stock>100}>
                    Incrementar stock
                </button>
            </div>

        </div>
      ) : (
        <div></div>
      )}
      
    </div>
  );
}