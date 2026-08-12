import axios from "axios";

const API_URL = "http://localhost:8083/api/auth";

export const register = (user) => {
    return axios.post(
        `${API_URL}/register`,
        user
    );
};

export const login = (credentials) => {
    return axios.post(
        `${API_URL}/login`,
        credentials
    );
};