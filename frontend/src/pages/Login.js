import { useState } from "react";
import { login } from "../services/authService";
import { Link, useNavigate } from "react-router-dom";

function Login() {

    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState("");

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const response = await login(form);
            setErrorMessage("");

            sessionStorage.setItem(
                "userId",
                response.data.userId
            );

            sessionStorage.setItem(
                "email",
                response.data.email
            );

            sessionStorage.setItem(
                "role",
                response.data.role
            );

            sessionStorage.setItem(
                "token",
                response.data.token
            );


            if (response.data.role === "ADMIN") {
                navigate("/admin");
            } else {
                navigate("/");
            }
        }
        catch (error) {
            setErrorMessage(error.response?.data?.message || "Invalid Credentials");
        }
    };

    return (
        <div className="auth-container">

            <div className="auth-card">

                <h2>Welcome Back 🌸</h2>

                <p className="auth-subtitle">
                    Login to continue your BeautyKart journey
                </p>

                <form onSubmit={handleSubmit}>

                    <input
                        type="email"
                        placeholder="Enter your email"
                        onChange={(e) =>
                            setForm({
                                ...form,
                                email: e.target.value
                            })
                        }
                    />

                    <input
                        type="password"
                        placeholder="Enter your password"
                        onChange={(e) =>
                            setForm({
                                ...form,
                                password: e.target.value
                            })
                        }
                    />

                    <button
                        type="submit"
                        className="auth-btn"
                    >
                        Login
                    </button>

                    {
                        errorMessage && (
                            <p className="error-message">
                                {errorMessage}
                            </p>
                        )
                    }

                    <p className="auth-link">
                        Don't have an account?

                        <Link to="/register">
                            Register
                        </Link>
                    </p>

                </form>

            </div>

        </div>
    );
}

export default Login;
