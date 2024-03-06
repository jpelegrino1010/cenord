import { useEffect } from "react";
import { connect } from "react-redux";
import { useNavigate } from "react-router-dom";

const Home = (props) => {
  const navigate = useNavigate();
  useEffect(() => {
    console.log("Cargo la pagina");
    navigate("/home");
  }, []);
  return <>Welcome to my Home page.</>;
};

export default connect()(Home);
