/**
 * The NavBar component in React handles navigation and logout functionality based on user
 * authentication status.
 * @returns The `NavBar` component is being returned. It is a functional component that represents a
 * navigation bar for a POS (Point of Sale) system. The navigation bar includes a link to the home
 * page, a logout button, and some styling using Bootstrap classes. The component also includes a
 * `useEffect` hook to check for a user token in the local storage and set it as a default header for
 * Axios
 */

import axios from 'axios';
import React, { useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'

export default function NavBar() {

    const navigate = useNavigate();

    const home = () => {
        navigate("/");
    }

    const logout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    }
    useEffect(() => {
        const usertoken = localStorage.getItem("token")
        if( !usertoken ){
            navigate("/login")
        }else{
            axios.defaults.headers.common['Authorization'] = `${usertoken}`;
        }
    }, [])
    

    return (
        <div>
            <div>
            <nav class="navbar navbar-expand-lg bg-secondary" data-bs-theme="dark">
                    <div class="container-fluid">
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                            <Link class="nav-link" to='/' style={{color:'white'}}>POS System</Link>
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                            </ul>
                            <form class="d-flex" role="search">
                                <button class="btn btn-primary" onClick={logout}>Logout</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    )
}
