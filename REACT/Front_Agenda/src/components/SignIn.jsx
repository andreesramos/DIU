import React, { useState } from "react";
import { signInWithEmailAndPassword } from "firebase/auth";
import { auth } from "../firebase";
import { Link, useNavigate } from "react-router-dom";
import "../styles/SignIn.css";
import logo from "../assets/logo_agenda.png";

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const signInWithEmailAndPasswordHandler = async (event) => {
    event.preventDefault();
    try {
      await signInWithEmailAndPassword(auth, email, password);
      navigate("/");
    } catch (error) {
      setError("Error signing in with email and password!");
      console.error("Error signing in:", error);
    }
  };

  const onChangeHandler = (event) => {
    const { name, value } = event.currentTarget;

    if (name === "userEmail") {
      setEmail(value);
    } else if (name === "userPassword") {
      setPassword(value);
    }
  };

  return (
    <div className="container d-flex justify-content-center align-items-center min-vh-100">
      <div className="card">
        <img src={logo} alt="Logo" className="logo" />
        <h2>Sign In</h2>
        {error !== null && <div className="error-message">{error}</div>}
        <form className="form" onSubmit={signInWithEmailAndPasswordHandler}>
          <input
            type="email"
            name="userEmail"
            value={email}
            placeholder="Email"
            id="userEmail"
            onChange={onChangeHandler}
          />
          <input
            type="password"
            name="userPassword"
            value={password}
            placeholder="Password"
            id="userPassword"
            onChange={onChangeHandler}
          />
          <button type="submit">Sign In</button>
        </form>
        <footer>
          Need an account? <Link to="/signUp">Sign up <span>here</span></Link>
        </footer>
      </div>
    </div>
  );
};

export default SignIn;
