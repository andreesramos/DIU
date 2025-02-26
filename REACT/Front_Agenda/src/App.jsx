import React, { Component } from "react";
import { Link, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddPerson from "./components/Add-person.component";
import EditPerson from "./components/Edit-person.component";
import PersonsList from "./components/Persons-list.component";
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import { PersonProvider } from "./context/PersonContext";
import { AuthProvider } from "./context/AuthContext";
import TutorialsList from "./components/Tutorials-list.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
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
            <li className="nav-item">
              <Link to={"/signIn"} className="nav-link">
                Login
              </Link>
            </li>
            {/*{user ? (
              <>
                <span className="mr-3">Bienvenido, {user.email}</span>
                <button className="btn btn-danger btn-sm" onClick={logout}>Logout</button>
              </>
            ) : (
              <Link className="btn btn-primary btn-sm" to="/signIn">Sign In</Link>
            )}*/}
          </div>
        </nav>

        <div className="container mt-3">
          <AuthProvider>
            <PersonProvider>
              <Routes>
                <Route path="/" element={<PersonsList />} />
                <Route path="/agenda" element={<PersonsList />} />
                <Route path="/add" element={<AddPerson />} />
                <Route path="/agenda/:id" element={<EditPerson />} />
                <Route path="/signIn" element={<SignIn/>} />
                <Route path="/signUp" element={<SignUp />} />
                <Route path="/tutoriales" element={<TutorialsList />} />
              </Routes>
            </PersonProvider>
          </AuthProvider>
        </div>
      </div>
    );
  }
}

export default App;
