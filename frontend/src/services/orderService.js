import axios from "axios";

const API_URL="http://localhost:8087/api/orders";

const getAuthHeader = () => ({
    headers: {
        Authorization: `Bearer ${sessionStorage.getItem("token")}`}
    });

export const placeOrder=(userId)=>{
    return axios.post(`${API_URL}/user/${userId}`);
};

export const getOrderByUserId=(userId)=>{
    return axios.get(`${API_URL}/user/${userId}`,getAuthHeader());
};

export const getOrderByOrderId=(orderId)=>{
    return axios.get(`${API_URL}/${orderId}`,getAuthHeader());
};

export const updateOrderStatus=(orderId, status)=>{
    return axios.put(`${API_URL}/${orderId}/status`,{orderStatus: status});
};

//ADMIN
export const getAllOrders = () => {
    return axios.get(
        `${API_URL}/admin`,
        getAuthHeader()
    );
};

export const updateOrderStatusAdmin = (
    orderId,
    status
) => {

    return axios.put(
        `${API_URL}/${orderId}/status`,
        {
            orderStatus: status
        },
        getAuthHeader()
    );
};