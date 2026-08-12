import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";

function Profile() {

    const email = sessionStorage.getItem("email");
    const role = sessionStorage.getItem("role");
    const navigate = useNavigate();

    const handleLogout = () => {

        sessionStorage.clear();

        navigate("/login");
    };

    return (
        <>
            <Navbar />

            <div className="profile-container">

                <div className="profile-card">

                    <div className="profile-avatar">
                        {
                            role === "ADMIN"
                                ? "👑"
                                : email?.charAt(0).toUpperCase()
                        }
                    </div>

                    <h2>My Profile</h2>

                    <p className="profile-subtitle">
                        Welcome to BeautyKart 🌸
                    </p>

                    <div className="profile-info">

                        <div className="profile-row">
                            <span>User ID</span>
                            <strong>
                                {sessionStorage.getItem("userId")}
                            </strong>
                        </div>

                        <div className="profile-row">
                            <span>Email</span>
                            <strong>{email}</strong>
                        </div>

                        <div className="profile-row">
                            <span>Role</span>
                            <strong>
                                {role}
                            </strong>
                        </div>

                    </div>

                    <button
                        className="profile-logout-btn"
                        onClick={handleLogout}
                    >
                        Logout
                    </button>
                </div>

            </div>
        </>
    );
}

export default Profile;