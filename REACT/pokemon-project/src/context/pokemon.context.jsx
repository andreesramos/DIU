import { createContext, useState } from "react";

const PokemonContext = createContext();

function PokemonProviderWrapper(props) {
  const [pokemons, setPokemons] = useState([]);

  const fetchPokemon = async (index) => {
    const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${index}`);
    const data = await response.json();
    return data;
  };

  return (
    <PokemonContext.Provider value={{ pokemons, setPokemons, fetchPokemon }}>
      {props.children}
    </PokemonContext.Provider>
  );
}

export { PokemonContext, PokemonProviderWrapper };
