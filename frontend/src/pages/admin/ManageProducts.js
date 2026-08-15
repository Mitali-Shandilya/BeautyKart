import { useEffect, useState } from "react";
import AdminNavbar from "../../components/AdminNavbar";

import {
    addProduct,
    updateProduct,
    deleteProduct,
    getAllProductsForAdmin,
    activateProduct
} from "../../services/productService";

import { getAllBrands } from "../../services/brandService";

import { getAllCategories } from "../../services/categoryService";

function ManageProducts() {

    const [products, setProducts] = useState([]);
    const [brands, setBrands] = useState([]);
    const [categories, setCategories] = useState([]);

    const [editingId, setEditingId] = useState(null);

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [imageUrl, setImageUrl] = useState("");
    const [brandId, setBrandId] = useState("");
    const [categoryId, setCategoryId] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

    useEffect(() => {
        loadProducts();
        loadBrands();
        loadCategories();
    }, []);

    const loadProducts = async () => {
        try {

            const response =
                await getAllProductsForAdmin();

            setErrorMessage("");

            setProducts(response.data);

        } catch (error) {

            setErrorMessage(
                error.response?.data?.message ||
                "Unable to load products."
            );
        }
    };

    const loadBrands = async () => {
        const response = await getAllBrands();
        setBrands(response.data);
    };

    const loadCategories = async () => {
        const response = await getAllCategories();
        setCategories(response.data);
    };

    const handleSubmit = async () => {

        const product = {
            name,
            description,
            price: Number(price),
            quantity: Number(quantity),
            imageUrl,
            categoryId: Number(categoryId),
            brandId: Number(brandId),
            active: true
        };

        try {

            if (editingId) {

                await updateProduct(
                    editingId,
                    product
                );

                await loadProducts();

                setErrorMessage("");

                setSuccessMessage(
                    "Product updated successfully."
                );

                setEditingId(null);

            } else {

                await addProduct(product);

                await loadProducts();

                setErrorMessage("");

                setSuccessMessage(
                    "Product added successfully."
                );
            }

            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);

            clearForm();



        } catch (error) {

            setErrorMessage(
                error.response?.data?.message ||
                "Unable to save product."
            );
        }

    };

    const handleEdit = (product) => {
        setEditingId(product.id);
        setName(product.name);
        setDescription(product.description);
        setPrice(product.price);
        setQuantity(product.quantity);
        setImageUrl(product.imageUrl);
        setBrandId(product.brand?.id || "");
        setCategoryId(product.category?.id || "");
    };

    const handleDelete = async (id) => {

        const confirmed = window.confirm(
            "Are you sure you want to delete this product?"
        );

        if (!confirmed) {
            return;
        }

        try {

            await deleteProduct(id);

            await loadProducts();

            setErrorMessage("");

            setSuccessMessage(
                "Product deleted successfully."
            );

            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);

        } catch (error) {

            setErrorMessage(
                error.response?.data?.message ||
                "Unable to delete product."
            );
        }
    };

    const clearForm = () => {

        setName("");
        setDescription("");
        setPrice("");
        setQuantity("");
        setImageUrl("");
        setBrandId("");
        setCategoryId("");
    };
    const handleActivate = async (id) => {

        try {

            await activateProduct(id);

            await loadProducts();

            setErrorMessage("");

            setSuccessMessage(
                "Product activated successfully."
            );

            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);

        } catch (error) {

            setErrorMessage(
                error.response?.data?.message ||
                "Unable to activate product."
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
            <div className="products-form">

                <h2>Manage Products</h2>

                <input
                    type="text"
                    placeholder="Product Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />

                <input
                    type="number"
                    placeholder="Price"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                />

                <input
                    type="number"
                    placeholder="Quantity"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Image URL"
                    value={imageUrl}
                    onChange={(e) => setImageUrl(e.target.value)}
                />

                <select
                    value={brandId}
                    onChange={(e) => setBrandId(e.target.value)}
                >
                    <option value="">Select Brand</option>

                    {brands.map(brand => (
                        <option
                            key={brand.id}
                            value={brand.id}
                        >
                            {brand.name}
                        </option>
                    ))}
                </select>

                <select
                    value={categoryId}
                    onChange={(e) => setCategoryId(e.target.value)}
                >
                    <option value="">Select Category</option>

                    {categories.map(category => (
                        <option
                            key={category.id}
                            value={category.id}
                        >
                            {category.name}
                        </option>
                    ))}
                </select>

                <button
                    className="add-btn"
                    onClick={handleSubmit}
                >
                    {
                        editingId
                            ? "Update Product"
                            : "Add Product"
                    }
                </button>

            </div>

            <div className="products-grid">

                {products.length === 0 ? (
                    <div className="empty-products">
                        💄 No products found.
                    </div>
                ) : (
                    products.map((product) => (

                        <div
                            className={`product-card ${product.active
                                ? "active-card"
                                : "inactive-card"
                                }`}
                            key={product.id}
                        >

                            <h3>{product.name}</h3>

                            <img src={product.imageUrl} alt={product.name}
                                className="product-image" />


                            <p>
                                Description: {product.description}
                            </p>

                            <p>
                                Brand: {product.brand?.name || "N/A"}
                            </p>

                            <p>
                                Category: {product.category?.name || "N/A"}
                            </p>

                            <p className="product-price">
                                ₹ {product.price}
                            </p>

                            <p
                                className={
                                    product.quantity === 0
                                        ? "out-of-stock"
                                        : product.quantity < 5
                                            ? "low-stock"
                                            : "in-stock"
                                }
                            >
                                Stock: {product.quantity}
                            </p>

                            {product.quantity === 0 && (
                                <p className="out-of-stock">
                                    OUT OF STOCK
                                </p>
                            )}

                            <p
                                className={
                                    product.active
                                        ? "status-active"
                                        : "status-inactive"
                                }
                            >
                                {
                                    product.active
                                        ? "ACTIVE"
                                        : "INACTIVE"
                                }
                            </p>

                            {product.active ? (

                                <div className="product-actions">

                                    <button
                                        className="edit-btn"
                                        onClick={() =>
                                            handleEdit(product)
                                        }
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="delete-btn"
                                        onClick={() =>
                                            handleDelete(product.id)
                                        }
                                    >
                                        Delete
                                    </button>

                                </div>

                            ) : (

                                <button
                                    className="activate-btn"
                                    onClick={() =>
                                        handleActivate(product.id)
                                    }
                                >
                                    Activate
                                </button>

                            )}

                        </div>

                    ))
                )}

            </div>

        </>
    );
}

export default ManageProducts;