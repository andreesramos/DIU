import { useEffect, useState } from "react"
import "./PokemonCard.css";

function PokemonCard(props){
    const {pokemon, selectPokemon} = props;

    return pokemon.id ? (
        <li className="pokemon-card" onClick={() => selectPokemon(pokemon)}>
            <h2 className="pokemon-name">{pokemon.name}</h2>
            <img src={pokemon.sprites.front_default} alt="pokemon img" className="pokemon-img" />
            <h3 className="text">HP: {pokemon.stats[0].base_stat}</h3>
        </li>
    ) : (
        <p className="loading">Loading...</p>
    )
    
}

export default PokemonCard