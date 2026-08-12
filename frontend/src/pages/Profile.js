import Navbar from "../components/Navbar";

function Profile() {

    return (
        <>
            <Navbar />

            <div className="profile-card">

                <h2>My Profile</h2>

                <p>
                    User Id:
                    {sessionStorage.getItem("userId")}
                </p>

                <p>
                    Email:
                    {sessionStorage.getItem("email")}
                </p>

                <p>
                    Role:
                    {sessionStorage.getItem("role")}
                </p>

            </div>
        </>
    );
}

export default Profile;