import React, { Component } from "react";
import { Link, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddPerson from "./components/add-person.component";
import EditPerson from "./components/edit-person.component";
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
          <Routes>
            <Route path="/" element={<PersonsList />} />
            <Route path="/agenda" element={<PersonsList />} />
            <Route path="/add" element={<AddPerson />} />
            <Route path="/agenda/:id" element={<EditPerson />} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;
