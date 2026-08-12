import AdminNavbar from "../../components/AdminNavbar";

export default function AdminDashboard() {
    return (
        <>
            <AdminNavbar />

            <div className="dashboard-container">

                <h2>Admin Dashboard</h2>

                <p className="dashboard-subtitle">
                    Manage Products, Brands and Categories
                </p>

                <div className="dashboard-grid">

                    <div className="dashboard-card">
                        <h3>Products</h3>
                        <p>Add, Update, Delete and Activate Products</p>
                    </div>

                    <div className="dashboard-card">
                        <h3>Brands</h3>
                        <p>Manage Active and Inactive Brands</p>
                    </div>

                    <div className="dashboard-card">
                        <h3>Categories</h3>
                        <p>Manage Active and Inactive Categories</p>
                    </div>

                    <div className="dashboard-card">
                        <h3>Orders</h3>
                        <p>Manage Orders</p>
                    </div>

                </div>

            </div>
        </>
    );
}