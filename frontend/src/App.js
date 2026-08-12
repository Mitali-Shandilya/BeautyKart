import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Products from './pages/Products';
import Home from './pages/Home';
import Cart from './pages/Cart';
import Orders from './pages/Orders';
import AdminDashboard from './pages/admin/AdminDashboard';
import ManageBrands from './pages/admin/ManageBrands';
import ManageCategories from './pages/admin/ManageCategories';
import ManageProducts from './pages/admin/ManageProducts';
import Login from './pages/Login';
import Register from './pages/Register';
import Profile from './pages/Profile';
import ProtectedRoute from './services/ProtectedRoute';
import ManageOrders from './pages/admin/ManageOrders';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/products" element={<Products/>}/>
        <Route path="/cart" element={<ProtectedRoute><Cart/></ProtectedRoute>}/>
        <Route path="/orders" element={<ProtectedRoute><Orders/></ProtectedRoute>}/>
        <Route path="/admin" element={<AdminDashboard />}/>
        <Route path="/admin/brands" element={<ProtectedRoute><ManageBrands /></ProtectedRoute>}/>
        <Route path="/admin/categories" element={<ProtectedRoute><ManageCategories /></ProtectedRoute>}/>
        <Route path="/admin/products" element={<ProtectedRoute><ManageProducts/></ProtectedRoute>}/>
        <Route path="/admin/orders" element={<ProtectedRoute><ManageOrders/></ProtectedRoute>}/>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register/>}/>
        <Route path="/profile" element={<ProtectedRoute>
          <Profile/>
          </ProtectedRoute>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;