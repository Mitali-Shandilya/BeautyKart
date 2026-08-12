import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { register } from "../services/authService";

function Register() {

    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState("");

    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        password: ""
    });

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            await register(form);

            alert("Registration Successful!");

            navigate("/login");

        } catch (error) {

            console.error(error);

            if (error.response) {

                console.log("STATUS:", error.response.status);

                console.log("DATA:", error.response.data);

                alert(JSON.stringify(error.response.data));

            } else {

                alert(error.message);
            }
        }
    };

    return (
        <div className="auth-container">

            <div className="auth-card">

                <div className="auth-logo">
                    🌸
                </div>

                <h2>Create Your Account</h2>

                <p className="auth-subtitle">
                    Join BeautyKart and explore premium beauty products
                </p>

                <form onSubmit={handleSubmit}>

                    <input
                        type="text"
                        name="firstName"
                        placeholder="First Name"
                        value={form.firstName}
                        onChange={handleChange}
                    />

                    <input
                        type="text"
                        name="lastName"
                        placeholder="Last Name"
                        value={form.lastName}
                        onChange={handleChange}
                    />

                    <input
                        type="email"
                        name="email"
                        placeholder="Email Address"
                        value={form.email}
                        onChange={handleChange}
                    />

                    <input
                        type="text"
                        name="phoneNumber"
                        placeholder="Phone Number"
                        value={form.phoneNumber}
                        onChange={handleChange}
                    />

                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={form.password}
                        onChange={handleChange}
                    />

                    <button
                        type="submit"
                        className="auth-btn"
                    >
                        Create Account
                    </button>

                    <p className="auth-link">
                        Already have an account?
                        <Link to="/login"> Login</Link>
                    </p>

                </form>

                <p className="beauty-tagline">
                    ✨ Discover Beauty, Confidence & Elegance ✨
                </p>

            </div>

        </div>
    );
}

export default Register;