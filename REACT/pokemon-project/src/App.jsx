import { useState } from "react";
import "./App.css";
import PokemonList from "./components/PokemonList";
import PokemonDetails from "./components/PokemonDetails";
import PokemonDetails2 from "./components/PokemonDetails2";
import DetailsWrapper from "./hoc/DetailsWrapper";

function App() {
  const [selectedPokemon, setSelectedPokemon] = useState();
  const [selectedPokemon2, setSelectedPokemon2] = useState();

  const getDetails1 = (likes, increaseLikes) => {
    return (
      <PokemonDetails
        pokemon={selectedPokemon}
        likes={likes}
        increaseLikes={increaseLikes}
      ></PokemonDetails>
    );
  };

  const getDetails2 = (likes, increaseLikes) => {
    return (
      <PokemonDetails2
        pokemon={selectedPokemon2}
        likes={likes}
        increaseLikes={increaseLikes}
      ></PokemonDetails2>
    );
  };

  return (
    <main className="main">
      <h2>Pokemons Seleccionados</h2>
      {selectedPokemon && (
        <DetailsWrapper render={getDetails1}></DetailsWrapper>
      )}
      {selectedPokemon2 && (
        <DetailsWrapper render={getDetails2}></DetailsWrapper>
      )}
      <h2>Lista de Pokemons</h2>
      <PokemonList
        selectPokemon={setSelectedPokemon}
        selectPokemon2={setSelectedPokemon2}
      ></PokemonList>
    </main>
  );
}

export default App;
