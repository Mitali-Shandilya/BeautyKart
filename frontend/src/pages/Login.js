import { useState } from "react";
import { login } from "../services/authService";
import { Link, useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";

function Login() {

    const navigate = useNavigate();
    const [errors, setErrors] = useState({});
    const location = useLocation();

    const [errorMessage, setErrorMessage] = useState(
        location.state?.message || ""
    );

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            setErrors({});
            setErrorMessage("");


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

            console.log("FULL ERROR =", error);

            if (error.response) {

                const data = error.response.data;

                if (data.errors) {

                    setErrors(data.errors);

                } else {

                    setErrorMessage(
                        "Incorrect email or password."
                    );
                }

            } else {

                setErrorMessage(
                    "Unable to login. Please try again."
                );
            }
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
                    {
                        errorMessage && (

                            <div className="error-message">

                                {errorMessage}

                            </div>

                        )
                    }

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
                    {
                        errors.email &&
                        <span className="field-error">
                            {errors.email}
                        </span>
                    }

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
                    {
                        errors.password &&
                        <span className="field-error">
                            {errors.password}
                        </span>
                    }

                    <button
                        type="submit"
                        className="auth-btn"
                    >
                        Login
                    </button>

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
