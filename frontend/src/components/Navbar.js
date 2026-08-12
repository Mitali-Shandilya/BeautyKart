import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();
    const token = sessionStorage.getItem("token");
    const role = sessionStorage.getItem("role");

    const handleLogout = () => {

        sessionStorage.clear();

        navigate("/login");
    };

    return (

        <nav className="navbar">

            <Link to="/">Home</Link>

            <Link to="/products">Products</Link>
            {!token ? (
                <Link to="/login">
                    Account
                </Link>
            ) : (
                <>
                    <Link to="/cart">Cart</Link>
                    <Link to="/orders">Orders</Link>
                    <Link to="/profile">My Profile</Link>

                    {role === "ADMIN" && (
                        <Link to="/admin">
                            Admin Panel
                        </Link>
                    )}

                    <button
                        className="logout-btn"
                        onClick={handleLogout}
                    >
                        Logout
                    </button>
                </>
            )}

        </nav>
    );
}

export default Navbar;