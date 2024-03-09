/**
 * The AdminHome component renders an admin dashboard with clickable sections for categories, items,
 * stocks, orders, and customers.
 * @returns The `AdminHome` component is being returned. It contains JSX elements for an admin
 * dashboard with navigation links to different sections such as Categories, Items, Stocks, Orders, and
 * Customers. The component also includes a navigation function `navigateto` that redirects to the
 * specified URL when a section is clicked.
 */

import React from 'react'
import './Admin.css'
import AdminNavBar from './components/AdminNavBar'
import { useNavigate } from 'react-router-dom'

export default function AdminHome() {
  const navigate = useNavigate()
  const navigateto = (url) =>{
    navigate( "/admin" + url)
  }

  return (
    <div>
      <AdminNavBar />

      <div className="admin-home">
        <div className="admin-home-content">
          <h1>Admin Dashboard</h1>

          <div class="container">
            <div class="row columns-3">
              <div onClick={()=>navigateto("/categories")} class="col m-4 d-flex flex-row justify-content-center align-items-center" style={{ backgroundColor: "Teal", color: "white", height: "200px", borderRadius: "10px" }}>
                <h3>Categories</h3>
              </div>
              <div onClick={()=>navigateto("/items")} class="col m-4 d-flex flex-row justify-content-center align-items-center" style={{ backgroundColor: "Teal", color: "white", height: "200px", borderRadius: "10px"  }}>
                <h3>Items</h3>
              </div>
              <div onClick={()=>navigateto("/stocks")} class="col m-4 d-flex flex-row justify-content-center align-items-center" style={{ backgroundColor: "Teal", color: "white", height: "200px", borderRadius: "10px"  }}>
                <h3>Stocks</h3>
              </div>
            </div>

            <div class="row columns-3">
              <div onClick={()=>navigateto("/orders")} class="col m-4 d-flex flex-row justify-content-center align-items-center" style={{ backgroundColor: "Teal", color: "white", height: "200px", borderRadius: "10px"  }}>
                <h3>Orders</h3>
              </div>
              <div onClick={()=>navigateto("/customers")} class="col m-4 d-flex flex-row justify-content-center align-items-center" style={{ backgroundColor: "Teal", color: "white", height: "200px", borderRadius: "10px"  }}>
                <h3>Customers</h3>
              </div>
              <div class="col m-4 d-flex flex-row justify-content-center align-items-center" style={{ height: "200px" }}>
                
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  )
}
