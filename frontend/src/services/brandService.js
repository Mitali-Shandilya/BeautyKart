import axios from "axios";

const API_URL= "http://localhost:8085/api/brands";

export const getAllBrands=()=>{
    return axios.get(API_URL);
};

export const addBrand=(brand)=>{
    return axios.post(API_URL, brand);
};

export const getBrandByid=(id)=>{
    return axios.get(`${API_URL}/${id}`);
};

export const searchBrandByName=(name)=>{
    return axios.get(`${API_URL}/by-name/${name}`);
};

export const updateBrand=(id,brand)=>{
    return axios.put(`${API_URL}/${id}`,brand);
};

export const deleteBrand=(id)=>{
    return axios.delete(`${API_URL}/${id}`)
};

//ADMIN
export const getAllBrandsForAdmin = () => {
    return axios.get(`${API_URL}/admin`);
};

export const activateBrand = (id) => {
    return axios.put(`${API_URL}/admin/${id}/activate`);
};