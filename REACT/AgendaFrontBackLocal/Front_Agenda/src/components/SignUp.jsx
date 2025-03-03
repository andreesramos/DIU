import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { auth, generateUserDocument } from "../firebase";
import { createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import "../styles/Login.css";
import logo from "../assets/logo_agenda.png";

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [displayName, setDisplayName] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const createUserWithEmailAndPasswordHandler = async (event) => {
    event.preventDefault();
    setError(null);
    try {
      if (!email || !password || !displayName) {
        setError("Se requieren todos los campos");
        return;
      }

      const userCredential = await createUserWithEmailAndPassword(auth, email, password);
      const user = userCredential.user;

      await updateProfile(user, {
        displayName: displayName
      });

      await user.reload();
      await generateUserDocument(user, { displayName });
      navigate("/signIn");
    } catch (error) {
      setError("Error signing up");
      console.error("Error creating user: ", error);
    }
  };

  return (
    <div className="login-container"> 
      <div className="card">
        <img src={logo} alt="Logo" className="logo" />
        <h2>Sign Up</h2>
        {error && <div className="error-message">{error}</div>}
        <form className="form" onSubmit={createUserWithEmailAndPasswordHandler}>
          <input
            type="text"
            value={displayName}
            placeholder="Name"
            onChange={(e) => setDisplayName(e.target.value)}
          />
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
          <button type="submit">Sign Up</button>
        </form>
        <footer>
          ¿Tienes una cuenta? <Link to="/signIn">Accede <span>aqui</span></Link>
        </footer>
      </div>
    </div>
  );
};

export default SignUp;
