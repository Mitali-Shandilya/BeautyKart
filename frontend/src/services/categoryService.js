import axios from "axios";

const API_URL= "http://localhost:8084/api/categories";

export const addCategory=(category)=>{
    return axios.post(API_URL,category);
};

export const getAllCategories=()=>{
    return axios.get(API_URL);
};

export const getCategoryById=(id)=>{
    return axios.get(`${API_URL}/${id}`);
};

export const updateCategory=(id, category)=>{
    return axios.put(`${API_URL}/${id}`,category);
};

export const deleteCategory=(id)=>{
    return axios.delete(`${API_URL}/${id}`);
};

export const searchCategoryByName=(name)=>{
    return axios.get(`${API_URL}/by-name/${name}`);
};

//ADMIN
export const getAllCategoriesForAdmin = () => {
    return axios.get(`${API_URL}/admin`);
};

export const activateCategory = (id) => {
    return axios.put(`${API_URL}/admin/${id}/activate`);
};