import { useEffect, useState } from "react";
import { activateBrand, addBrand, deleteBrand, getAllBrandsForAdmin, updateBrand } from "../../services/brandService";
import AdminNavbar from "../../components/AdminNavbar";

function ManageBrands() {
    const [brands, setBrands] = useState([]);
    const [brandName, setBrandName] = useState("");
    const [editingId, setEditingId] = useState(null);
    const [country, setCountry] = useState("");

    useEffect(() => {
        loadBrands();
    }, []);

    const loadBrands = async () => {
        const response = await getAllBrandsForAdmin();
        setBrands(response.data);
    };

    const handleSubmit = async () => {

        const brand = {
            name: brandName,
            country: country,
            description: "",
            active: true
        };

        try {

            if (editingId) {
                await updateBrand(editingId, brand);
                setEditingId(null);
            }
            else {
                await addBrand(brand);
            }

            setBrandName("");
            setCountry("");

            loadBrands();

        } catch (error) {
            console.error(error);

            if (error.response) {
                console.log(error.response.data);
            }
        }
    };

    const handleEdit = (brand) => {
        setEditingId(brand.id);
        setBrandName(brand.name);
        setCountry(brand.country);
    };


    const handleDelete = async (id) => {

        const confirmed = window.confirm(
            "Are you sure you want to delete this brand?"
        );

        if (!confirmed) return;

        try {
            await deleteBrand(id);
            loadBrands();
        }
        catch (error) {
            console.error(error);
        }
    };

    const handleActivate = async (id) => {
        await activateBrand(id);
        loadBrands();
    };
    return (
        <>
            <AdminNavbar />
            <div className="brands-container">
                <div className="brand-form">
                    <h2>Manage Brands</h2>
                    <input
                        type="text"
                        value={brandName}
                        placeholder="Brand Name"
                        onChange={(e) => setBrandName(e.target.value)}
                    />

                    <input
                        type="text"
                        value={country}
                        placeholder="Country"
                        onChange={(e) => setCountry(e.target.value)}
                    />

                    <button
                        className="add-btn"
                        onClick={handleSubmit}
                    >
                        {editingId ? "Update Brand" : "Add Brand"}
                    </button>
                </div>

                <div className="brand-list">

                    {brands.length === 0 ? (
                        <p>No brands found.</p>
                    ) : (

                        brands.map(brand => (
                            <div
                                className={`brand-card ${brand.active
                                    ? "active-card"
                                    : "inactive-card"
                                    }`}
                                key={brand.id}
                            >
                                <h3>{brand.name}</h3>

                                <p className="brand-country">
                                    Country: {brand.country || "N/A"}
                                </p>

                                <p
                                    className={
                                        brand.active
                                            ? "status-active"
                                            : "status-inactive"
                                    }
                                >
                                    {brand.active ? "ACTIVE" : "INACTIVE"}
                                </p>

                                {brand.active ? (
                                    <div className="brand-actions">
                                        <button
                                            className="edit-btn"
                                            onClick={() => handleEdit(brand)}
                                        >
                                            Edit
                                        </button>

                                        {" "}

                                        <button
                                            className="delete-btn"
                                            onClick={() => handleDelete(brand.id)}
                                        >
                                            Delete
                                        </button>

                                    </div>
                                ) : (
                                    <button
                                        className="activate-btn"
                                        onClick={() => handleActivate(brand.id)}
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
export default ManageBrands;