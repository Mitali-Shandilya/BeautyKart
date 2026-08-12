import AdminNavbar from "../../components/AdminNavbar";

export default function AdminDashboard() {
    return (
        <>
            <AdminNavbar />
            <div className="admin-hero">

                <h1>🌸 BeautyKart Admin Panel</h1>

                <p>
                    Manage products, inventory and orders
                </p>

            </div>

            <div className="stats-grid">

                <div className="stat-card">
                    <h3>Products</h3>
                    <span>120</span>
                </div>

                <div className="stat-card">
                    <h3>Orders</h3>
                    <span>45</span>
                </div>

                <div className="stat-card">
                    <h3>Brands</h3>
                    <span>12</span>
                </div>

                <div className="stat-card">
                    <h3>Categories</h3>
                    <span>8</span>
                </div>

            </div> े
        </>
    );
}