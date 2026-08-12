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

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        try {
            const response = await getAllCategoriesForAdmin();
            setCategories(response.data);
        }
        catch (error) {
            console.error(error);
        }
    };

    const handleSubmit = async () => {

        const category = {
            name: categoryName
        };

        try {

            if (editingId) {
                await updateCategory(
                    editingId,
                    category
                );

                setEditingId(null);
            }
            else {
                await addCategory(category);
            }

            setCategoryName("");

            loadCategories();

        }
        catch (error) {
            console.error(error);
        }
    };

    const handleEdit = (category) => {

        setEditingId(category.id);

        setCategoryName(
            category.name
        );
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
            loadCategories();
        }
        catch (error) {
            console.error(error);
        }
    };

    const handleActivate = async (id) => {
        await activateCategory(id);
        loadCategories();
    };
    return (
        <>
            <AdminNavbar />

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
                        <p>No categories found.</p>
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