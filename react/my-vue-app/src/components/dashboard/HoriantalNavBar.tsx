import React from "react";
import { useNavigate } from "react-router-dom";

const HorizontalNavBar = () => {

const navigate = useNavigate();

  return (
    <div className='NavigationBar'>
      <img src="..\src\assets\images\pharmaImage.jpg" alt="aaa" />
      <button className="logout-button" onClick={() => navigate('/')}> Log Out </button>
      {/* <a href='/' className="logout-button">LOG OUT</a> */}
    </div>

  );
};

export default HorizontalNavBar;
