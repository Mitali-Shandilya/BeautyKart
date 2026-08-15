import { useEffect, useState } from "react";
import AdminNavbar from "../../components/AdminNavbar";
import { getAllOrders, updateOrderStatusAdmin } from "../../services/orderService";

function ManageOrders() {

    const [orders, setOrders] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

    useEffect(() => {
        loadOrders();
    }, []);

    const loadOrders = async () => {
        try {

            const response = await getAllOrders();

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

    const handleStatusUpdate = async (
        orderId,
        status
    ) => {

        try {

            await updateOrderStatusAdmin(
                orderId,
                status
            );

            await loadOrders();

            setErrorMessage("");

            setSuccessMessage(
                `Order #${orderId} updated to ${status}`
            );

            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);

        } catch (error) {

            setErrorMessage(
                error.response?.data?.message ||
                "Unable to update order status."
            );
        }
    };


    return (
        <>
            <AdminNavbar />

            {
                errorMessage && (
                    <div className="error-message">
                        ⚠️ {errorMessage}
                    </div>
                )
            }

            {
                successMessage && (
                    <div className="success-message">
                        ✅ {successMessage}
                    </div>
                )
            }
            <div className="manage-orders-container">

                <h2 className="manage-orders-title">
                    Manage Orders
                </h2>

                {
                    orders.length === 0 ? (

                        <div className="empty-orders">
                            📦 No orders found.
                        </div>

                    ) : (

                        <div className="admin-orders-grid">

                            {orders.map(order => (

                                <div
                                    key={order.orderId}
                                    className="admin-order-card"
                                >

                                    <div className="order-top">

                                        <h3>
                                            Order #{order.orderId}
                                        </h3>

                                        <span className="order-user">
                                            User #{order.userId}
                                        </span>

                                    </div>

                                    <p
                                        className={`status-badge ${order.orderStatus === "PLACED"
                                            ? "status-placed"
                                            : order.orderStatus === "SHIPPED"
                                                ? "status-shipped"
                                                : order.orderStatus === "DELIVERED"
                                                    ? "status-delivered"
                                                    : "status-cancelled"
                                            }`}
                                    >
                                        {order.orderStatus}
                                    </p>

                                    <select
                                        className="admin-status-select"
                                        defaultValue={order.orderStatus}
                                        onChange={(e) =>
                                            handleStatusUpdate(
                                                order.orderId,
                                                e.target.value
                                            )
                                        }
                                    >
                                        <option value="PLACED">
                                            PLACED
                                        </option>

                                        <option value="SHIPPED">
                                            SHIPPED
                                        </option>

                                        <option value="DELIVERED">
                                            DELIVERED
                                        </option>

                                        <option value="CANCELLED">
                                            CANCELLED
                                        </option>
                                    </select>

                                    <div className="admin-order-items">

                                        {order.items.map(item => (

                                            <div
                                                key={item.productId}
                                                className="admin-order-item"
                                            >

                                                <img src={item.imageUrl} alt={item.imageUrl} />

                                                <div className="admin-item-details">

                                                    <p>
                                                        {item.productName}
                                                    </p>

                                                    <p>
                                                        Qty: {item.quantity}
                                                    </p>

                                                    <p>
                                                        ₹ {item.price}
                                                    </p>

                                                </div>

                                            </div>

                                        ))}

                                    </div>

                                    <div className="admin-order-total">
                                        ₹ {order.totalAmount}
                                    </div>

                                </div>

                            ))}

                        </div>

                    )
                }

            </div>
        </>
    );
}

export default ManageOrders;