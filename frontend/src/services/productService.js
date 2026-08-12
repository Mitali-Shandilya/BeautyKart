import axios from "axios";

const API_URL="http://localhost:8083/api/products";
const getAuthHeader = () => ({
    headers: {
        Authorization: `Bearer ${sessionStorage.getItem("token")}`
    }
});

console.log("TOKEN =", sessionStorage.getItem("token"));

export const getAllProducts = () => {
    return axios.get(
        API_URL,
        getAuthHeader()
    );
};

//search product by name
export const searchProduct = (name) => {
    return axios.get(
        `${API_URL}/search?name=${name}`,
        getAuthHeader()
    );
};

//filter by brand
export const getProductsByBrand = (brandId) => {
    return axios.get(
        `${API_URL}/brand/${brandId}`,
        getAuthHeader()
    );
};

//filter by category
export const getProductsByCategory = (categoryId) => {
    return axios.get(
        `${API_URL}/category/${categoryId}`,
        getAuthHeader()
    );
};

export const addProduct = (product) => {
    return axios.post(
        API_URL,
        product,
        getAuthHeader()
    );
};

export const updateProduct = (id, product) => {
    return axios.put(
        `${API_URL}/${id}`,
        product,
        getAuthHeader()
    );
};

export const deleteProduct = (id) => {
    return axios.delete(
        `${API_URL}/${id}`,
        getAuthHeader()
    );
};

//ADMIN
export const getAllProductsForAdmin = () => {
    return axios.get(`${API_URL}/admin`,getAuthHeader());
};

export const activateProduct = (id) => {
    return axios.put(`${API_URL}/admin/${id}/activate`,{},getAuthHeader());
};
