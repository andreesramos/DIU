import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./PokemonPage.css"

function PokemonPage() {
  const { id } = useParams();
  const [pokemon, setPokemon] = useState();

  useEffect(() => {
    fetchPokemon(id);
  }, [id]);

  const fetchPokemon = async (id) => {
    const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${id}`);
    const data = await response.json();
    setPokemon(data);
  };

  const navigate = useNavigate();

  const goTo = (id) => {
    navigate(`/pokemon/${id}`)
  }

  return (
    <section id="pokemon-page">
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
            <button onClick={() => goTo(Number(id) - 1)} className="btn">⬅️</button>
            <button onClick={() => goTo(Number(id) + 1)} className="btn">➡️</button>
          </div>
        </div>
      ) : (
        <div>
          <h2>Cargando...</h2>
        </div>
      )}
    </section>
  );
}

export default PokemonPage;
