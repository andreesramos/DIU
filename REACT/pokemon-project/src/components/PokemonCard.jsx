import "./PokemonCard.css";
import { useEffect, useState } from "react";

function PokemonCard(props) {
    const {pokemon} = props;

  return pokemon.id ? (
    <li className="pokemon-card">
      <h2 className="pokemon-name">{pokemon.name}</h2>
      <img
        src={pokemon.sprites.front_default}
        alt="pokemon img"
        className="pokemon-img"
      />
      <h3 className="text">HP: {pokemon.stats[0].base_stat}</h3>
    </li>
  ) : (
    <p className="loading">Loading...</p>
  );
}

export default PokemonCard;
