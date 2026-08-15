import { useEffect, useState } from "react";
import AdminNavbar from "../../components/AdminNavbar";
import {
    addCategory,
    updateCategory,
    deleteCategory,
    getAllCategoriesForAdmin,
    activateCategory
} from "../../services/categoryService";

function ManageCategories() {

    const [categories, setCategories] = useState([]);
    const [categoryName, setCategoryName] = useState("");
    const [editingId, setEditingId] = useState(null);
    const [errorMessage, setErrorMessage] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [selectedCategory, setSelectedCategory] = useState(null);

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        try {
            const response = await getAllCategoriesForAdmin();
            setErrorMessage("");
            setCategories(response.data);
        }
        catch (error) {
            setErrorMessage(
                error.response?.data?.message ||
                "Unable to load categories."
            );
        }
    };

    const handleSubmit = async () => {

        const category = editingId ? {
            ...selectedCategory,
            name: categoryName
        } : {
            name: categoryName
        };

        try {

            if (editingId) {

                await updateCategory(
                    editingId,
                    category
                );

                await loadCategories();

                setErrorMessage("");

                setSuccessMessage(
                    "Category updated successfully."
                );

                setEditingId(null);

            } else {

                await addCategory(category);

                await loadCategories();

                setErrorMessage("");

                setSuccessMessage(
                    "Category added successfully."
                );
            }

            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);

            setCategoryName("");
        }
        catch (error) {
            setErrorMessage(
                error.response?.data?.message ||
                "Unable to save category."
            );
        }
    };

    const handleEdit = (category) => {

        setSelectedCategory(category);

        setEditingId(category.id);

        setCategoryName(category.name);
    };

    const handleDelete = async (id) => {

        const confirmed = window.confirm(
            "Are you sure you want to delete this category?"
        );

        if (!confirmed) {
            return;
        }

        try {
            await deleteCategory(id);
            setErrorMessage("");
            await loadCategories();
            setSuccessMessage("Category deleted successfully!");
            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);

        }
        catch (error) {
            setErrorMessage(
                error.response?.data?.message ||
                "Unable to delete category."
            );

        }
    };

    const handleActivate = async (id) => {
        try {
            await activateCategory(id);
            setErrorMessage("");
            await loadCategories();
            setSuccessMessage(
                "Category activated successfully."
            );
            setTimeout(() => {
                setSuccessMessage("");
            }, 3000);
        } catch (error) {
            setErrorMessage(
                error.response?.data?.message ||
                "Unable to activate category."
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
            <div className="categories-container">

                <div className="category-form">

                    <h2>Manage Categories</h2>

                    <input
                        type="text"
                        value={categoryName}
                        placeholder="Category Name"
                        onChange={(e) =>
                            setCategoryName(e.target.value)
                        }
                    />

                    <button
                        className="add-btn"
                        onClick={handleSubmit}
                    >
                        {
                            editingId
                                ? "Update Category"
                                : "Add Category"
                        }
                    </button>

                </div>



                <div className="category-list">

                    {categories.length === 0 ? (
                        <div className="empty-categories">
                            📂 No categories found.
                        </div>
                    ) : (

                        categories.map(category => (

                            <div
                                className={`category-card ${category.active
                                    ? "active-card"
                                    : "inactive-card"
                                    }`}
                                key={category.id}
                            >

                                <h3>
                                    {category.name}
                                </h3>
                                <p
                                    className={
                                        category.active
                                            ? "status-active"
                                            : "status-inactive"
                                    }
                                >
                                    {category.active ? "ACTIVE" : "INACTIVE"}
                                </p>

                                {category.active ? (
                                    <div className="category-actions">

                                        <button
                                            className="edit-btn"
                                            onClick={() =>
                                                handleEdit(category)
                                            }
                                        >
                                            Edit
                                        </button>

                                        <button
                                            className="delete-btn"
                                            onClick={() =>
                                                handleDelete(category.id)
                                            }
                                        >
                                            Delete
                                        </button>

                                    </div>
                                ) : (
                                    <button
                                        className="activate-btn"
                                        onClick={() =>
                                            handleActivate(category.id)
                                        }
                                    >
                                        Activate
                                    </button>
                                )}

                            </div>
                        ))
                    )}
                </div>
            </div>
        </>
    );
}

export default ManageCategories;