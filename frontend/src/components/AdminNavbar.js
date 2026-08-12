import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function AdminNavbar() {
    const Navigate = useNavigate();
    const [showMenu, setShowMenu] = useState(false);
    const email = sessionStorage.getItem("email");

    const handleLogout = () => {

        sessionStorage.clear();

        Navigate("/login");
    };
    return (
        <div className="admin-navbar">

            <Link to="/admin">
                Dashboard
            </Link>

            <Link to="/admin/products">
                Products
            </Link>

            <Link to="/admin/brands">
                Brands
            </Link>

            <Link to="/admin/categories">
                Categories
            </Link>

            <Link to="/admin/orders">
                Orders
            </Link>
            <div className="admin-menu">

                <button
                    className="admin-menu-btn"
                    onClick={() => setShowMenu(!showMenu)}
                >
                    👑 Admin ▼
                </button>

                {
                    showMenu && (
                        <div className="admin-dropdown">

                            <div className="admin-email">
                                {email}
                            </div>

                            <button
                                onClick={handleLogout}
                            >
                                Logout
                            </button>

                        </div>
                    )
                }

            </div>


        </div>
    );
}
export default AdminNavbar;