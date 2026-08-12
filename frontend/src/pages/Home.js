import Navbar from "../components/Navbar";

function Home() {
    return (
        <>
            <Navbar />

            <div className="home-container">

                <h1>BeautyKart 💄</h1>

                <p className="home-subtitle">
                    Discover Beauty, Confidence & Elegance
                </p>

                <p className="home-description">
                    Explore premium skincare, makeup,
                    perfumes and beauty essentials from
                    trusted brands.
                </p>

            </div>

            <div className="features-section">

                <h2>Why Choose BeautyKart?</h2>

                <div className="features-grid">

                    <div className="feature-card">
                        ✨
                        <h3>Premium Products</h3>
                    </div>

                    <div className="feature-card">
                        🚚
                        <h3>Fast Delivery</h3>
                    </div>

                    <div className="feature-card">
                        💯
                        <h3>Authentic Cosmetics</h3>
                    </div>

                    <div className="feature-card">
                        💖
                        <h3>Customer Satisfaction</h3>
                    </div>

                </div>

            </div>

            <div className="contact-section">

                <h2>Contact Our Team</h2>

                <div className="contact-grid">

                    <div className="contact-card">
                        📧
                        <p>support@beautykart.com</p>
                    </div>

                    <div className="contact-card">
                        📞
                        <p>+91 98765 43210</p>
                    </div>

                    <div className="contact-card">
                        📍
                        <p>Mumbai, India</p>
                    </div>

                </div>

            </div>

        </>
    );
}

export default Home;