import { useState } from "react";
import { login } from "../services/authService";
import { Link, useNavigate } from "react-router-dom";

function Login() {

    const navigate = useNavigate();

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const response = await login(form);

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
            alert("Invalid Credentials");
        }
    };

    return (
        <div>
            <h2>Login</h2>

            <form onSubmit={handleSubmit}>

                <input
                    type="email"
                    placeholder="Email"
                    onChange={(e) =>
                        setForm({
                            ...form,
                            email: e.target.value
                        })
                    }
                />

                <input
                    type="password"
                    placeholder="Password"
                    onChange={(e) =>
                        setForm({
                            ...form,
                            password: e.target.value
                        })
                    }
                />

                <button type="submit">
                    Login
                </button>

                <p>
                    Don't have an account?{" "}
                    <Link to="/register">
                        Register
                    </Link>
                </p>

            </form>
        </div>
    );
}

export default Login;
