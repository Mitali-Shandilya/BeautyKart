import { Link, useNavigate } from "react-router-dom";

function AdminNavbar() {
    const Navigate=useNavigate();
    const handleLogout = () => {

        sessionStorage.clear();

        Navigate("/login");
    };
    return (
        <div className="admin-navbar">
            <Link to="/admin">Dashboard</Link>
            <Link to="/admin/products">Products</Link>
            <Link to="/admin/brands">Brands</Link>
            <Link to="/admin/categories">Categories</Link>
            <Link to="/admin/orders">Orders</Link>
            <button
                        className="logout-btn"
                        onClick={handleLogout}
                    >
                        Logout
                    </button>
        </div>
    );
}
export default AdminNavbar;