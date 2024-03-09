/**
 * The App function sets up routes for different pages in a web application using React Router.
 * @returns The App component is being returned. It contains routing setup using BrowserRouter and
 * Routes from react-router-dom. Various Route components are defined for different paths such as
 * '/admin', '/admin/categories', '/admin/items', '/admin/stocks', '/admin/orders', '/admin/customers',
 * '/invoice', '/', '/login', and '/register', each rendering a specific component for that path like
 * AdminHome, AdminCategory
 */

import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AdminHome from './adminpages/AdminHome';
import AdminCustomer from './adminpages/AdminCustomer';
import AdminOrder from './adminpages/AdminOrder';
import Home from './customerpages/Home';
import AdminCategory from './adminpages/Categories/AdminCategory';
import AdminStocks from './adminpages/Stocks/AdminStocks';
import AdminItems from './adminpages/Items/AdminItems';
import LoginN from './customerpages/Auth/LoginN';
import RegisterN from './customerpages/Auth/Registern';
import Invoice from './customerpages/Invoice/Invoice';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>


            <Route path='/admin' element={<AdminHome />} />
            <Route path='/admin/categories' element={<AdminCategory />} />
            <Route path='/admin/items' element={<AdminItems />} />
            <Route path='/admin/stocks' element={<AdminStocks />} />
            <Route path='/admin/orders' element={<AdminOrder />} />
            <Route path='/admin/customers' element={<AdminCustomer />} />
            <Route path='/invoice' element={<Invoice />} />

            

            <Route path='/' element={<Home />} />
       
          <Route path='/login' element={<LoginN />} />
          <Route path='/register' element={<RegisterN />} />
        
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
