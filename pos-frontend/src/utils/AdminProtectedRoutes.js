/**
 * The function `AdminProtectedRoutes` checks for a token in local storage and sets the Authorization
 * header for axios requests before rendering nested routes.
 * @returns The `AdminProtectedRoutes` component is returning the `Outlet` component from React Router.
 */

import axios from 'axios';
import React from 'react'
import { Outlet, useNavigate } from 'react-router-dom';

const AdminProtectedRoutes = () => {

    const token = localStorage.getItem("token");

    const navigate = useNavigate();

    if (!token) {
        navigate("/admin/login");
    }

    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

  return <Outlet/>
}

export default AdminProtectedRoutes;