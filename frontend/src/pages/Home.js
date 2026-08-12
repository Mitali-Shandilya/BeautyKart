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
                    Welcome to BeautyKart Cosmetics Store.
                    Explore premium skincare, makeup,
                    beauty essentials and much more.
                </p>

            </div>
        </>
    );
}

export default Home;