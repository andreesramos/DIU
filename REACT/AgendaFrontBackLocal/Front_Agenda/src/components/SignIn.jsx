import React, { useState } from "react";
import { signInWithEmailAndPassword } from "firebase/auth";
import { auth } from "../firebase";
import { Link, useNavigate } from "react-router-dom";
import "../styles/Login.css";
import logo from "../assets/logo_agenda.png";
import { useAuth } from "../context/UserContext";

const SignIn = () => {
  const { setUser } = useAuth();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const signInWithEmailAndPasswordHandler = async (event) => {
    event.preventDefault();
    try {
      const userCredential=await signInWithEmailAndPassword(auth, email, password);
      const user=userCredential.user;

      await user.reload();
      const updatedUser = auth.currentUser;
      setUser(updatedUser);

      navigate("/");
    } catch (error) {
      setError("Error signing in with email and password!");
      console.error("Error signing in:", error);
    }
  };

  return (
    <div className="login-container"> 
      <div className="card">
        <img src={logo} alt="Logo" className="logo" />
        <h2>Sign In</h2>
        {error && <div className="error-message">{error}</div>}
        <form className="form" onSubmit={signInWithEmailAndPasswordHandler}>
          <input
            type="email"
            value={email}
            placeholder="Email"
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="password"
            value={password}
            placeholder="Password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="submit">Sign In</button>
        </form>
        <footer>
          ¿No tienes cuenta? <Link to="/signUp">Registrate <span>aqui</span></Link>
        </footer>
      </div>
    </div>
  );
};

export default SignIn;
