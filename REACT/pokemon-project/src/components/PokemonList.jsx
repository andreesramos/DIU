import { useEffect, useState } from "react"
import PokemonCard from "./PokemonCard"
import "./PokemonList.css"

function PokemonList(props){
    const [pokemons, setPokemons] = useState([]);

    useEffect(() => {
        getPokemons(10);
    }, [])

    const fetchPokemon = async (index) => {
        const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${index}`)
        const data = await response.json()
        return data;
    }

    const getPokemons = async (quantity) => {
        const pkmnArr = [];

        for (let i=1; i<=quantity; i++){
            const pokemon = await fetchPokemon(i);
            pkmnArr.push(pokemon);
        }

        setPokemons(pkmnArr);
    }

    const pokemonCards =pokemons.map((pokemon) => {
        return <PokemonCard key={pokemon.id} pokemon={pokemon} selectPokemon={props.selectPokemon}></PokemonCard>
    })

    return (
        <ul className="pokemon-list">
            {pokemonCards}
        </ul>
    )
}

export default PokemonList