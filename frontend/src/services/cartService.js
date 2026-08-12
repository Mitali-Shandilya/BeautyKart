import axios from "axios";

const API_URL = "http://localhost:8086/api/cart";

//add item to cart 
export const addToCart=(cartItem)=>{
    return axios.post(API_URL, cartItem);
}

//fetch all the items from cart by usrId
export const getCartByUserId=(userId)=>{
    return axios.get(`${API_URL}/user/${userId}`);
};

//update the quantity of cart item by cartItemId
export const updateCartItem=(id, cartItem)=>{
    return axios.put(`${API_URL}/${id}`,cartItem);
};

//delete a cartItem by it's cartItemId
export const deleteCartItem=(id)=>{
    return axios.delete(`${API_URL}/${id}`);
};

//clear a cart
export const clearCart=(userId)=>{
    return axios.delete(`${API_URL}/user/${userId}`);
};