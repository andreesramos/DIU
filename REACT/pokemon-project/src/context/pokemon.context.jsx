import { createContext, useState } from "react";

const PokemonContext = createContext();

function PokemonProviderWrapper(props) {
    const [pokemons, setPokemons] = useState([])

  return (
    <PokemonContext.Provider value={{pokemons, setPokemons}}>
      {props.children}
    </PokemonContext.Provider>
  );
}

export { PokemonContext, PokemonProviderWrapper };
