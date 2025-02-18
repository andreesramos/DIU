import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { auth, generateUserDocument } from "../firebase";
import { createUserWithEmailAndPassword } from "firebase/auth";

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
    <div className="mt-8">
      <h1 className="text-3xl mb-2 text-center font-bold">Sign Up</h1>
      <div className="border border-blue-400 mx-auto w-11/12 md:w-2/4 rounded py-8 px-4 md:px-8">
        {error && (
          <div className="py-4 bg-red-600 text-yellow-300 w-full text-center mb-3">
            {error}
          </div>
        )}
        <form>
          <label htmlFor="displayName" className="block">Display Name:</label>
          <input
            type="text"
            className="my-1 p-1 w-full"
            name="displayName"
            value={displayName}
            placeholder="E.g: Pepe"
            id="displayName"
            onChange={(e) => setDisplayName(e.target.value)}
          />
          <label htmlFor="userEmail" className="block">Email:</label>
          <input
            type="email"
            className="my-1 p-1 w-full"
            name="userEmail"
            value={email}
            placeholder="E.g: prueba@gmail.com"
            id="userEmail"
            onChange={(e) => setEmail(e.target.value)}
          />
          <label htmlFor="userPassword" className="block">Password:</label>
          <input
            type="password"
            className="mt-1 mb-3 p-1 w-full"
            name="userPassword"
            value={password}
            placeholder="Your Password"
            id="userPassword"
            onChange={(e) => setPassword(e.target.value)}
          />
          <button
            className="bg-green-400 hover:bg-green-500 w-full py-2 text-white"
            onClick={createUserWithEmailAndPasswordHandler}
          >
            Sign up
          </button>
        </form>
        <p className="text-center my-3">or</p>
        <p className="text-center my-3">
          Already have an account?{" "}
          <Link to="/signIn" className="text-blue-500 hover:text-blue-600">
            Sign in here
          </Link>
        </p>
      </div>
    </div>
  );
};

export default SignUp;
