import { useEffect, useState } from "react";
import { getOrderByUserId } from "../services/orderService";
import Navbar from "../components/Navbar";

function Orders() {
    const [orders, setOrders] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        loadOrders();
    }, []);

    const loadOrders = async () => {
        try {

            const userId = sessionStorage.getItem("userId");

            console.log("USER ID =", userId);

            const response = await getOrderByUserId(userId);

            console.log("RESPONSE =", response.data);
            setErrorMessage("");
            setOrders(response.data);

        } catch (error) {

            setOrders([]);

            setErrorMessage(
                error.response?.data?.message ||
                "Unable to load orders."
            );
        }
    };
    return (
        <>
            <Navbar />
            {
                errorMessage && (
                    <div className="error-message">
                        ⚠️ {errorMessage}
                    </div>
                )
            }
            <div className="orders-header">
                <h2>My Orders</h2>
            </div>


            <div className="orders-grid">

                {
                    orders.length === 0 ? (
                        <div className="empty-orders">
                            📦 No orders found.
                            <p>Your order history will appear here.</p>
                        </div>
                    ) : (
                        orders.map(order => (
                            <div
                                className="order-card"
                                key={order.orderId}
                            >
                                <h3>Order #{order.orderId}</h3>
                                <p
                                    className={
                                        order.orderStatus === "CANCELLED"
                                            ? "status-inactive"
                                            : "status-active"
                                    }
                                >
                                    {order.orderStatus}
                                </p>
                                <p className="order-total">
                                    ₹ {order.totalAmount}
                                </p>
                                <p>Date: {new Date(order.orderDate).toLocaleString()}</p>
                                <h4 className="order-items-heading">
                                    Items
                                </h4>
                                {order.items.map(item => (
                                    <div
                                        className="order-item"
                                        key={item.productId}
                                    >

                                        {item.imageUrl && (
                                            <img src={item.imageUrl} alt={item.productName} className="order-item-image" />
                                        )}

                                        <p>{item.productName}</p>

                                        <p>
                                            Quantity: {item.quantity}
                                        </p>

                                        <p>
                                            Price: ₹{item.price}
                                        </p>

                                        <p>
                                            Sub Total: ₹{item.subTotal}
                                        </p>

                                    </div>
                                ))}

                            </div>
                        ))
                    )
                }

            </div >

        </>

    );
}
export default Orders;