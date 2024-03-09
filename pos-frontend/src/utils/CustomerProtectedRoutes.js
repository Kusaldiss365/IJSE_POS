/**
 * The function `CustomerProtectedRoutes` checks for a token in local storage and sets it as a header
 * for axios requests before rendering the child components.
 * @returns The CustomerProtectedRoutes component is being returned, which includes the Outlet
 * component from React Router.
 */

import axios from 'axios';
import React from 'react'
import { Outlet, useNavigate } from 'react-router-dom';

const CustomerProtectedRoutes = () => {

    
    const token = localStorage.getItem("token");

    const navigate = useNavigate();

    if (!token) {
        navigate("/login");
    }

    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    return <Outlet/>

}

export default CustomerProtectedRoutes;