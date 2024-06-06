import React from "react";
import { auth, user } from "../Firebase";
import { signOut } from "firebase/auth";
import { Navigate } from 'react-router-dom';

const Logout = () => {
    try {
        signOut(auth);
        user.setUID(null);
    } catch(e) { console.log(e.message) }
    return (
        <div>
            <h1>Signout</h1>
            <Navigate to="/" />
        </div>
    )
}

export default Logout