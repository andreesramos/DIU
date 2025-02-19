import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { auth, generateUserDocument } from "../firebase";
import { createUserWithEmailAndPassword } from "firebase/auth";
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
    try{
      if(!email || !password || !displayName){
        setError("All fields required");
        return;
      }

      const userCredential = await createUserWithEmailAndPassword(auth, email, password);
      const user = userCredential.user;

      await generateUserDocument(user, {displayName});
      navigate("/signIn");
    }
    catch(error){
      setError('Error Signing up');
      console.error("Error creating user: ", error);
    }
      
    setEmail("");
    setPassword("");
    setDisplayName("");
  };

  // const onChangeHandler = event => {
  //   const { name, value } = event.currentTarget;

  //   if (name === "userEmail") {
  //     setEmail(value);
  //   } else if (name === "userPassword") {
  //     setPassword(value);
  //   } else if (name === "displayName") {
  //     setDisplayName(value);
  //   }
  // };

  return (
    <div className="container d-flex justify-content-center align-items-center min-vh-100">
      <div className="card">
        <img src={logo} alt="Logo" className="logo" />
        <h2>Sign Up</h2>
        {error !== null && <div className="error-message">{error}</div>}
        <form className="form" onSubmit={createUserWithEmailAndPasswordHandler}>
          <input
            type="text"
            name="displayName"
            value={displayName}
            placeholder="Name"
            id="displayName"
            onChange={(e) => setDisplayName(e.target.value)}
          />
          <input
            type="email"
            name="userEmail"
            value={email}
            placeholder="Email"
            id="userEmail"
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="password"
            name="userPassword"
            value={password}
            placeholder="Password"
            id="userPassword"
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="submit">
            Sign up
          </button>
        </form>
        <footer>
          Already have an account? <Link to="/signIn">Sign in <span>here</span></Link>
        </footer>
      </div>
    </div>
  );
};

export default SignUp;
