import { Route, Routes } from "react-router-dom";
import "./App.css";
import HomePage from "./pages/HomePage";
import { lazy, Suspense } from "react";
// import PokemonsPage from "./pages/PokemonsPage";
// import PokemonPage from "./pages/PokemonPage";
// import ErrorPage from "./pages/ErrorPage";

const PokemonsPage = lazy(() => import("./pages/PokemonsPage"))
const PokemonPage = lazy(() => import("./pages/PokemonPage"))
const ErrorPage = lazy(() => import("./pages/ErrorPage"))


function App() {
  return(
    <Suspense fallback={<h1>Cargando...</h1>}>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/pokemons" element={<PokemonsPage />} />
        <Route path="/pokemons/:id" element={<PokemonPage />} />

        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </Suspense>
  )
}

export default App;
