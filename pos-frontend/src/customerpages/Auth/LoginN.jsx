/**
 * The `LoginN` function in this JavaScript React component handles user login functionality, including
 * form submission, authentication, and redirection based on user role.
 * @returns The `LoginN` component is being returned. It is a functional component that contains a form
 * for user login. The form includes input fields for email address and password, a submit button for
 * logging in, and a link to create a new account. The component also handles state for loading status,
 * username, and password using the `useState` hook. The `login` function is responsible for making a
 */

import axios from 'axios';
import React, { useState } from 'react'
import { Spinner } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';

export default function LoginN() {
    const [loading, setLoading] = useState(false)
    const [UserName, setUserName] = useState("kusal.admin@gmail.com");
    const [password, setPassword] = useState("1234");

    const navigate = useNavigate();

    const login = async (event) => {
        setLoading(true)
        event.preventDefault();
        const data = {
            "username": UserName,
            "password": password
        }
        const response = await axios.post("http://localhost:8080/login", data);
        setLoading(false)
        if (response.status === 200) {
            localStorage.setItem("token", response.headers.authorization);
            axios.defaults.headers.common['Authorization'] = `${response.headers.authorization}`;
            if (UserName === "kusal.admin@gmail.com") {
                localStorage.setItem("role", "admin");
                window.location.href = "/admin"
            } else {
                localStorage.setItem("role", "customer");
                window.location.href = "/"
            }

        } else {
            console.log("login error!");
        }
    }

    const register = () => {
        navigate("/register");
    }
    return (
        <div className="auth-wrapper">
            <div className="auth-inner">
                <form onSubmit={login} >
                    <h3>Sign In</h3>
                    <div className="mb-3">
                        <label>Email address</label>
                        <input
                            value={UserName}
                            onChange={(e) => setUserName(e.target.value)}
                            type="email"
                            className="form-control"
                            placeholder="Enter email"
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label>Password</label>
                        <input
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            type="password"
                            className="form-control"
                            placeholder="Enter password"
                            required
                        />
                    </div>
                    <div className="d-grid">
                        <button type="submit" className="btn btn-primary">
                            {loading ?
                                <Spinner animation="border" role="status" style={{ color: "white", width: "15px", height: "15px" }}>
                                    <span className="visually-hidden">Loading...</span>
                                </Spinner> : "Log in"}

                        </button>
                        <Link to="/register">Create account</Link>
                    </div>

                </form>
            </div>
        </div>
    )
}
