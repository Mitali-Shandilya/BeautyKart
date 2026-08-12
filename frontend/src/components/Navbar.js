import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();
    const token = sessionStorage.getItem("token");
    const role = sessionStorage.getItem("role");
    const email = sessionStorage.getItem("email");

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

                    <Link to="/orders">
                        Orders
                    </Link>

                    <Link to="/profile" className="profile-nav">

                        <span className="profile-circle">
                            {email?.charAt(0).toUpperCase()}
                        </span>

                        My Profile

                    </Link>

                    
                </>
            )}

        </nav>
    );
}

export default Navbar;