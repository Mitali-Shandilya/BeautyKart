import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { register } from "../services/authService";

function Register() {

    const navigate = useNavigate();
    const [errors, setErrors] = useState({});
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

            setErrors({});
            setErrorMessage("");

            await register(form);

            alert("Registration Successful!");

            navigate("/login");

        } catch (error) {

            console.log("FULL ERROR =", error);

            if (error.response) {

                console.log(
                    "RESPONSE DATA =",
                    error.response.data
                );

                const data = error.response.data;

                if (data.errors) {

                    console.log(
                        "VALIDATION ERRORS =",
                        data.errors
                    );

                    setErrors(data.errors);

                } else {

                    setErrorMessage(
                        data.message
                    );
                }

            } else {

                setErrorMessage(
                    "Something went wrong"
                );
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
                    {
                        errorMessage && (

                            <div className="error-message">

                                {errorMessage}

                            </div>

                        )
                    }

                    <input
                        type="text"
                        name="firstName"
                        placeholder="First Name"
                        value={form.firstName}
                        onChange={handleChange}
                    />

                    {
                        errors.firstName &&
                        <span className="field-error">
                            {errors.firstName}
                        </span>
                    }


                    <input
                        type="text"
                        name="lastName"
                        placeholder="Last Name"
                        value={form.lastName}
                        onChange={handleChange}
                    />

                    {
                        errors.lastName &&
                        <span className="field-error">
                            {errors.lastName}
                        </span>
                    }


                    <input
                        type="email"
                        name="email"
                        placeholder="Email Address"
                        value={form.email}
                        onChange={handleChange}
                    />

                    {
                        errors.email && <span className="field-error">{errors.email}</span>
                    }

                    <input
                        type="text"
                        name="phoneNumber"
                        placeholder="Phone Number"
                        value={form.phoneNumber}
                        onChange={handleChange}
                    />

                    {
                        errors.phoneNumber &&
                        <span className="field-error">
                            {errors.phoneNumber}
                        </span>
                    }


                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={form.password}
                        onChange={handleChange}
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