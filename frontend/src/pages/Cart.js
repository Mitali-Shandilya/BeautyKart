import { useEffect, useState } from "react";
import { clearCart, deleteCartItem, getCartByUserId, updateCartItem } from "../services/cartService";
import Navbar from "../components/Navbar";
import { placeOrder } from "../services/orderService";

function Cart() {
    const [items, setItems] = useState([]);

    useEffect(() => {
        loadCart();
    }, []);

    const loadCart = async () => {
        try {
            const userId = sessionStorage.getItem("userId");
            const response = await getCartByUserId(userId);
            setItems(response.data);
        }
        catch (error) {
            console.error(error);
        }
    };

    const handleUpdateQuantity = async (id, item) => {

    if (item.quantity >= item.product.quantity) {

        alert(
            `Only ${item.product.quantity} items available in stock`
        );

        return;
    }

    const request = {
        userId: item.userId,
        productId: item.productId,
        quantity: item.quantity + 1
    };

    try {
        await updateCartItem(id, request);
        loadCart();
    } catch (error) {
        console.error(error);
    }
};

    const handleDecreaseQuantity = async (id, item) => {
        if (item.quantity === 1) {
            await deleteCartItem(id);
            loadCart();
            return;
        }

        const request = {
            userId: item.userId,
            productId: item.productId,
            quantity: item.quantity - 1
        };

        try {
            await updateCartItem(id, request);
            loadCart();
        }
        catch (error) {
            console.error(error);
        }
    };

    const handleDelete = async (id) => {
        console.log("Deleting:", id);
        try {
            await deleteCartItem(id);
            loadCart();
        }
        catch (error) {
            console.error(error);
        }
    };

    const handleClearCart = async () => {
        const confirmed = window.confirm("Are you sure you want to clear the cart?");
        if (!confirmed) {
            return;
        }
        try {
            await clearCart(sessionStorage.getItem("userId"));
            loadCart();
        }
        catch (error) {
            console.error(error);
        }
    };

    const handlePlaceOrder = async () => {
        try {
            const response = await placeOrder(sessionStorage.getItem("userId"));

            console.log(`Order placed successfully.
                Order Id: ${response.data.orderId}`);
            loadCart();
        }
        catch (error) {
            console.error(error);
        }
    };

    return (
        <>
            <Navbar />
            <div className="cart-header">

                <h2>My Cart</h2>

                <div className="cart-header-actions">

                    <button
                        className="delete-btn"
                        onClick={handleClearCart}
                    >
                        Clear Cart
                    </button>

                    <button
                        className="add-btn"
                        onClick={handlePlaceOrder}
                    >
                        Place Order
                    </button>

                </div>

            </div>

            {items.length === 0 ? (
                <p>Your cart is empty.</p>
            ) : (
                <div className="cart-grid">

                    {items.map(item => (

                        <div className="cart-card" key={item.id}>
                            {item.product?.imageUrl && (
                                <img src={item.product.imageUrl} className="cart-item-image" />
                            )}
                            <h3>{item.product.name}</h3>

                            <p>Price: ₹ {item.product.price}</p>

                            <p>Quantity: {item.quantity}</p>

                            <p className="cart-total">Total: ₹ {item.totalPrice}</p>

                            <div className="quantity-controls">

                                <button
                                    className="edit-btn"
                                    onClick={() =>
                                        handleUpdateQuantity(item.id, item)
                                    }
                                >
                                    +
                                </button>

                                <span className="quantity-value">
                                    {item.quantity}
                                </span>

                                <button
                                    className="edit-btn"
                                    onClick={() =>
                                        handleDecreaseQuantity(item.id, item)
                                    }
                                >
                                    -
                                </button>

                            </div>

                            <button
                                className="delete-btn"
                                onClick={() =>
                                    handleDelete(item.id)
                                }
                            >
                                Remove
                            </button>
                        </div>
                    ))
                    }
                </div>
            )
            }
        </>
    );
}
export default Cart;