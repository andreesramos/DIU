import { Link } from "react-router-dom"
import logo from "../assets/logo-pokemon.png"
import "./HomePage.css"

function HomePage() {
  return (
    <section id="home-page">
        <h1 className="title">Bienvenidos</h1>
        <img src={logo} alt="Pokemon logo" className="logo"/>
        <Link to="/pokemons" className="link">Entrar</Link>
    </section>
  )
}

export default HomePage