import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { PokemonProviderWrapper } from "./context/pokemon.context.jsx";
import { BrowserRouter } from "react-router-dom";
import { UserProviderWrapper } from "./context/user.context.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <BrowserRouter>
      <UserProviderWrapper>
        <PokemonProviderWrapper>
          <App />
        </PokemonProviderWrapper>
      </UserProviderWrapper>
    </BrowserRouter>
  </StrictMode>
);
