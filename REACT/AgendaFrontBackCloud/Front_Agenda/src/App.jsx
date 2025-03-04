import React, { useState, useEffect } from "react";
import {
  Link,
  Route,
  Routes,
  useNavigate,
  useLocation,
} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddPerson from "./components/Add-person.component";
import EditPerson from "./components/Edit-person.component";
import PersonsList from "./components/Persons-list.component";
import AgendaDataService from "./services/agenda.service";
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import { PersonProvider } from "./context/PersonContext";
import { UserProvider, useAuth } from "./context/UserContext";
import TutorialsList from "./components/Tutorials-list.component";
import { signOut } from "firebase/auth";
import { auth } from "./firebase";

const Bubbles = () => (
  <div id="bubbles">
    <div className="bubbles__first" />
    <div className="bubbles__second" />
  </div>
);

const NavLink = ({ to, children }) => {
  const navigate = useNavigate();
  const location = useLocation();

  const handleClicked = (e) => {
    e.preventDefault();
    if (location.pathname === to) return; // No activar animación si ya estamos en la página

    const bubbles = document.getElementById("bubbles");
    if (!bubbles) return;

    bubbles.classList.remove("hide");
    bubbles?.classList.add("show");

    setTimeout(() => {
      navigate(to);
      bubbles.classList.remove("show");
      bubbles.classList.add("hide");
    }, 1000);
  };

  return (
    <Link
      to={to}
      className={`nav-link ${location.pathname === to ? "active" : ""}`}
      onClick={handleClicked}
    >
      {children}
    </Link>
  );
};

const Navbar = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const [dropdownOpen, setDropdownOpen] = useState(false);

  const handleLogout = async () => {
    try {
      await signOut(auth);
      navigate("/signIn");
    } catch (error) {
      console.error("Error al cerrar sesión:", error);
    }
  };

  return (
    <>
      <Bubbles />
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <Link to={"/agenda"} className="navbar-brand">
          Agenda
        </Link>
        {/* <NavLink to="/agenda">Agenda</NavLink> */}
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/agenda"} className="nav-link">
              Personas
            </Link>
            {/* <NavLink to="/agenda">Personas</NavLink> */}
          </li>
          {user && (
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Añadir
              </Link>
              {/* <NavLink to="/add">Añadir</NavLink> */}
            </li>
          )}
        </div>

        <div className="navbar-nav ml-auto d-flex align-items-center">
          {user ? (
            <>
              <div className="user-info d-flex align-items-center">
                <img
                  src={
                    user.photoURL ||
                    "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  }
                  alt="User"
                  className="user-photo"
                />

                <span className="user-name">
                  {user.displayName || user.email}
                </span>
              </div>
              <button
                className="nav-link btn btn-link logout-btn"
                onClick={handleLogout}
              >
                Logout
              </button>
            </>
          ) : (
            <div className="nav-item dropdown">
              <button
                className="nav-link dropdown-toggle btn btn-link"
                onClick={() => setDropdownOpen(!dropdownOpen)}
              >
                Perfil
              </button>
              <div className={`dropdown-menu ${dropdownOpen ? "show" : ""}`}>
                <Link to="/signIn" className="dropdown-item">
                  Sign In
                </Link>
                <Link to="/signUp" className="dropdown-item">
                  Sign Up
                </Link>
                {/* <NavLink to="/signIn">Sign In</NavLink>
                <NavLink to="/signUp">Sign Up</NavLink> */}
              </div>
            </div>
          )}
        </div>
      </nav>
    </>
  );
};

const App = () => {
  return (
    <UserProvider>
      <PersonProvider>
        <Navbar />
        <Routes>
          <Route path="/" element={<PersonsList />} />
          <Route path="/agenda" element={<PersonsList />} />
          <Route path="/add" element={<AddPerson />} />
          <Route path="/agenda/:id" element={<EditPerson />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route path="/tutoriales" element={<TutorialsList />} />
        </Routes>
      </PersonProvider>
    </UserProvider>
  );
};

export default App;
