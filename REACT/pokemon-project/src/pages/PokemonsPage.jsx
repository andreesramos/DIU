import { useContext, useState } from "react";
import PokemonList from "../components/PokemonList";
import PokemonDetails from "../components/PokemonDetails";
import PokemonDetails2 from "../components/PokemonDetails2";
import DetailsWrapper from "../hoc/DetailsWrapper";
import { Navigate } from "react-router-dom";
import { UserContext } from "../context/user.context";

function PokemonPages() {
  const { user, setUser } = useContext(UserContext);

  if (!user.isLoggedIn) return <Navigate to={"/error"} />;

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
      {user.name && (
        <section>
          <h2>Hola {user.name}</h2>
          <button onClick={() => setUser({ ...user, name: "Maria" })}>
            Change Name
          </button>
        </section>
      )}
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

export default PokemonPages;
