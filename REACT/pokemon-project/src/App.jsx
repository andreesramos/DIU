import { useState } from "react";
import "./App.css";
import PokemonList from "./components/PokemonList";
import PokemonDetails from "./components/PokemonDetails";
import PokemonDetails2 from "./components/PokemonDetails2";

function App() {
  const [selectedPokemon, setSelectedPokemon] = useState();
  const [selectedPokemon2, setSelectedPokemon2] = useState();

  return (
    <main className="main">
      <h2>Pokemons Seleccionados</h2>
      {selectedPokemon && (
        <PokemonDetails pokemon={selectedPokemon}></PokemonDetails>
      )}
      {selectedPokemon2 && (
        <PokemonDetails2 pokemon={selectedPokemon2}></PokemonDetails2>
      )}

      <h2>Lista de Pokemons</h2>

      <PokemonList
        selectPokemon={setSelectedPokemon}
        selectedPokemon2={setSelectedPokemon2}
      ></PokemonList>
    </main>
  );
}

export default App;
