import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddPerson from "./components/add-person.component";
//Componente para editar componente
//import Tutorial from "./components/tutorial.component";
import PersonsList from "./components/persons-list.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/agenda"} className="navbar-brand">
            Agenda
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/agenda"} className="nav-link">
                Personas
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Añadir
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
          {/*El en switch se renderizarán todas los compoentes cuta URL coicidan con la activa*/}
            <Route exact path={["/", "/agenda"]} component={PersonsList} />
            <Route exact path="/add" component={AddPerson} />
          {/*  <Route path="/tutorials/:id" component={Agenda} /> */}
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
