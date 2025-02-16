import { useContext, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import "./PokemonPage.css";
import { PokemonContext } from "../context/pokemon.context";

function PokemonPage() {
  const { fetchPokemon } = useContext(PokemonContext)

  const { id } = useParams();
  const [pokemon, setPokemon] = useState();
  const [error, setError] = useState();

  useEffect(() => {
    getPokemon(id);
  }, [id]);

  const getPokemon = async (id) => {
    try {
      const pokemon = await fetchPokemon(id)
      setPokemon(pokemon);
    } catch (e) {
      setError(e);
    }
  };

  const navigate = useNavigate();

  const goTo = (id) => {
    navigate(`/pokemons/${id}`);
  };

  return (
    <section id="pokemon-page">
      {error ? (
        <div>
          <h2>No se ha encontrado ningun pokemon</h2>
          <Link to={"/pokemons"}>Volver a la lista de pokemons</Link>
        </div>
      ) : (
        <>
          {pokemon ? (
            <div>
              <h2>{pokemon.name.toUpperCase()}</h2>
              <img
                src={pokemon.sprites.front_default}
                alt="pokemon img"
                className="pokemon-img"
              />
              <h3>HP: {pokemon.stats[0].base_stat}</h3>
              <h3>Attack: {pokemon.stats[1].base_stat}</h3>
              <h3>Defense: {pokemon.stats[2].base_stat}</h3>
              <div className="link-buttons">
                <button onClick={() => goTo(Number(id) - 1)} className="btn">
                  ⬅️
                </button>
                <button onClick={() => goTo(Number(id) + 1)} className="btn">
                  ➡️
                </button>
              </div>
            </div>
          ) : (
            <div>
              <h2>Cargando...</h2>
            </div>
          )}
        </>
      )}
    </section>
  );
}

export default PokemonPage;
