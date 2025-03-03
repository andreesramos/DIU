import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore, doc, getDoc, setDoc } from "firebase/firestore";

//////////////////////////////////////////////////////////

  // Your web app's Firebase configuration
  const firebaseConfig = {    
    apiKey: "AIzaSyBab7Y9biB1UNOcNHg3UNd99y__zGFL1aI",
    authDomain: "pruebalogin-7b4fd.firebaseapp.com",
    projectId: "pruebalogin-7b4fd",
    storageBucket: "pruebalogin-7b4fd.firebasestorage.app",
    messagingSenderId: "184415562113",
    appId: "1:184415562113:web:3dee8892b1c9c220b8e080"

  };
  // Initialize Firebase
  //firebase.initializeApp(firebaseConfig);

/////////////////////////////////////////////////////////
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const firestore = getFirestore(app);

export const generateUserDocument = async (user, additionalData) => {
  if (!user) return;

  const userRef = doc(firestore, `users/${user.uid}`);
  const snapshot = await getDoc(userRef);

  if (!snapshot.exists) {
    const { email, displayName, photoURL } = user;
    try {
      await setDoc(userRef, {
        displayName,
        email,
        photoURL,
        ...additionalData
      });
    } catch (error) {
      console.error("Error creating user document", error);
    }
  }
  return getUserDocument(user.uid);
};

const getUserDocument = async (uid) => {
  if (!uid) return null;
  try {
    const userRef = doc(firestore, `users/${uid}`);
    const userDocument = await getDoc(userRef);

    return {
      uid,
      ...userDocument.data()
    };
  } catch (error) {
    console.error("Error fetching user", error);
  }
};
