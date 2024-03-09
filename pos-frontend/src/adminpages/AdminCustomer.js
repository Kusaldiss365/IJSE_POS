/**
 * This React component fetches and displays a list of users from an API, allowing for user deletion
 * and navigation to a login page if unauthorized.
 * @returns The `AdminCustomer` component is being returned. It consists of JSX elements including an
 * `AdminNavBar`, a container with a list of users displayed in a table format. The users are fetched
 * from an API endpoint using Axios and displayed in the table. There is also a function `deleteUser`
 * to delete a user from the list. Additionally, there is a check for empty content using the `Empty
 */

import React, { useEffect, useState } from 'react'
import AdminNavBar from './components/AdminNavBar'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import EmptyContent from '../utils/EmptyContent';

export default function AdminCustomer() {

  const [users, setUsers] = useState([
    { id: 1, username: "asdasd", email: "2323" }
  ]);

  const navigate = useNavigate();

  useEffect(() => {
    getUsers();
  }, [])

  const getUsers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/v1/user/get-all");
      setUsers(response.data.data);
    } catch (error) {
      if (error.response.status === 401) {
        navigate("/admin/login");
      }
    }
  }

  const deleteUser = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/users/${id}`);
      const updatedUsers = users.filter((user) => user.id !== id);
      setUsers(updatedUsers);
    } catch (error) {
      console.error("Error deleting user:", error);
    }
  }


  return (
    <div>
      <AdminNavBar />


      <div className='container-md bg-white d-flex flex-column align-items-center'>
        <br />
        <h2>All Users</h2>
        <br />
        <div className='col-md-9 mt-4'>
          {users && users.length > 0 && (
            <table className="category-table">
              <colgroup>
                <col style={{ width: '10%' }} />
                <col style={{ width: '30%' }} />
                <col style={{ width: '50%' }} />
                <col style={{ width: '10%' }} />
              </colgroup>
              <thead>
                <tr>
                  <th style={{ textAlign: 'center' }}>ID</th>
                  <th style={{ textAlign: 'center' }}>Username</th>
                  <th style={{ textAlign: 'center' }}>Email</th>
                </tr>
              </thead>
              <tbody>
                {users.map((user) => (
                  <tr key={user.id}>
                    <td>{user.userId}</td>
                    <td>
                      {user.firstName} {user.secondName}
                    </td>
                    <td>
                      {user.email}
                    </td>
                    
                  </tr>
                ))}
              </tbody>
            </table>
          )}

          {EmptyContent(users)}
        </div>
      </div>
    </div>
  )
}
