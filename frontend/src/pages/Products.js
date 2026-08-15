import { useEffect, useState } from "react";
import { getAllProducts, getProductsByBrand, getProductsByCategory, searchProduct } from "../services/productService";
import Navbar from "../components/Navbar";
import { addToCart } from "../services/cartService";
import { getAllBrands } from "../services/brandService";
import { getAllCategories } from "../services/categoryService";
import { useNavigate } from "react-router-dom";

function Products() {
    const [products, setProducts] = useState([]);
    const [keyword, setKeyword] = useState("");
    const [brandId, setBrandId] = useState("");
    const [categoryId, setCategoryId] = useState("");
    const [brands, setBrands] = useState([]);
    const [categories, setCategories] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

    const loadProducts = async () => {

        const response = await getAllProducts();

        setErrorMessage("");
        setSuccessMessage("");

        setProducts(response.data);
    };

    const loadBrands = async () => {
        const response = await getAllBrands();
        setBrands(response.data);
    };

    const loadCategories = async () => {
        const response = await getAllCategories();
        setCategories(response.data);
    };

    const navigate = useNavigate();

    useEffect(() => {
        loadProducts();
        loadBrands();
        loadCategories();
    }, []);

    const handleSearch = async () => {
        if (keyword.trim() === "") {
            loadProducts();
            return;
        }
        try {

            const response = await searchProduct(keyword);

            setErrorMessage("");
            setSuccessMessage("");

            setProducts([response.data]);

        }
        catch (error) {

            setProducts([]);

            setErrorMessage(
                error.response?.data?.message ||
                `No product found for "${keyword}".`
            );
        }
    };

    const handleAddToCart = async (productId) => {

        const token = sessionStorage.getItem("token");

        if (!token) {

            navigate("/login", {
                state: {
                    message:
                        "Please login to add products to cart."
                }
            });
            return;
        }

        const cartItem = {
            userId: Number(
                sessionStorage.getItem("userId")
            ),
            productId: productId,
            quantity: 1
        };

        try {

            await addToCart(cartItem);

            setSuccessMessage(
                "Product added to cart successfully!"
            );
            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);

            setErrorMessage("");

        } catch (error) {

            console.error(error);
            setErrorMessage(
                error.response?.data?.message ||
                "Failed to add product to cart."
            );
        }
    };

    const handleBrandFilter = async () => {

        if (!brandId) {
            loadProducts();
            return;
        }

        try {

            const response =
                await getProductsByBrand(brandId);

            setErrorMessage("");
            setProducts(response.data);

        }
        catch (error) {

            setProducts([]);

            setErrorMessage(
                error.response?.data?.message ||
                "No products found."
            );
        }
    };

    const handleCategoryFilter = async () => {

        if (!categoryId) {
            loadProducts();
            return;
        }

        try {

            const response =
                await getProductsByCategory(categoryId);

            setErrorMessage("");
            setProducts(response.data);

        }
        catch (error) {

            setProducts([]);

            setErrorMessage(
                error.response?.data?.message ||
                "No products found."
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

            {
                successMessage && (
                    <div className="success-message">
                        ✅ {successMessage}
                    </div>
                )
            }
            <div className="product-filters">
                <div className="search-section">

                    <input
                        type="text"
                        value={keyword}
                        placeholder="Search Product"
                        onChange={(e) => setKeyword(e.target.value)}
                    />

                    <button
                        className="edit-btn"
                        onClick={handleSearch}
                    >
                        Search
                    </button>

                    <button
                        className="add-btn"
                        onClick={loadProducts}
                    >
                        Show All
                    </button>

                </div>
            </div>

            <div className="filter-section">
                <select
                    value={brandId}
                    onChange={(e) =>
                        setBrandId(e.target.value)
                    }
                >
                    <option value="">
                        Select Brand
                    </option>

                    {brands.map(brand => (
                        <option
                            key={brand.id}
                            value={brand.id}
                        >
                            {brand.name}
                        </option>
                    ))}
                </select>

                <button
                    className="edit-btn"
                    onClick={handleBrandFilter}
                >
                    Filter by Brand
                </button>
            </div>

            <div className="filter-section">
                <select
                    value={categoryId}
                    onChange={(e) =>
                        setCategoryId(e.target.value)
                    }
                >
                    <option value="">
                        Select Category
                    </option>

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
                    className="edit-btn"
                    onClick={handleCategoryFilter}
                >
                    Filter by Category
                </button>
            </div>
            <hr />

            <div className="user-products-container">

                <h2>Products</h2>
                {
                    products.length === 0 &&
                    !errorMessage && (

                        <div className="empty-products">
                            No products available.
                        </div>

                    )
                }
                {products.map((product) => {
                    return (
                        <div
                            className="product-card"
                            key={product.id}
                        >

                            <h3>{product.name}</h3>

                            <img src={product.imageUrl} />

                            <p>
                                Description: {product.description}
                            </p>

                            <p>
                                Brand: {product.brand?.name}
                            </p>

                            <p>
                                Category: {product.category?.name}
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
                                Available Stock: {product.quantity}
                            </p>

                            {product.quantity > 0 ? (
                                <button
                                    className="add-btn"
                                    onClick={() =>
                                        handleAddToCart(product.id)
                                    }
                                >
                                    Add To Cart
                                </button>
                            ) : (
                                <>
                                    <p className="out-of-stock">
                                        OUT OF STOCK
                                    </p>


                                    <button
                                        className="delete-btn"
                                        disabled
                                    >
                                        Out Of Stock
                                    </button>
                                </>
                            )}

                        </div>
                    );
                })}
            </div>
        </>
    );
}
export default Products;