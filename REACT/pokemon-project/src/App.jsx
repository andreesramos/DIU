import { Route, Routes } from "react-router-dom";
import "./App.css";
import HomePage from "./pages/HomePage";
import PokemonsPage from "./pages/PokemonsPage";
import PokemonPage from "./pages/PokemonPage";
import ErrorPage from "./pages/ErrorPage";

function App() {
  return(
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/pokemons" element={<PokemonsPage />} />
      <Route path="/pokemons/:id" element={<PokemonPage />} />

      <Route path="*" element={<ErrorPage />} />
    </Routes>
  )
}

export default App;
