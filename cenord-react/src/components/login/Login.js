import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import classes from "./login.module.css";
import { login } from "../../redux/actions/login/LoginAction";
import { useNavigate } from "react-router-dom";

const Login = (props) => {
  const [user, setUser] = useState({ username: "", password: "" });
  const { isLogin } = props;
  const navigate = useNavigate();

  useEffect(() => {
    if (isLogin) {
      navigate("/home");
    }
  }, [isLogin]);

  const inputChangeHandler = (e) => {
    const { name, value } = e.target;
    setUser((prevUser) => {
      return { ...prevUser, [name]: value };
    });
  };

  const submitHandler = (e) => {
    e.preventDefault();
    props.onLogin(user);
    console.log(user.username, " ", user.password, " ", props.isLogin);
  };

  return (
    <div className={classes.login}>
      <form onSubmit={submitHandler}>
        <div className={classes.element}>
          <div>
            <input
              type="text"
              name="username"
              value={user.username}
              placeholder="Username"
              onChange={inputChangeHandler}
            />
          </div>

          <div className={classes.input}>
            <input
              type="password"
              name="password"
              value={user.password}
              placeholder="Password"
              onChange={inputChangeHandler}
            />
          </div>

          <div>
            <button className={classes.button}>Sign In</button>
          </div>
        </div>
      </form>
    </div>
  );
};

const mapPropsToState = (state) => {
  return {
    isLogin: state.login.isLogin || false,
  };
};

const mapDispatchToState = (dispatch) => {
  return {
    onLogin: (user) => dispatch(login(user)),
  };
};

export default connect(mapPropsToState, mapDispatchToState)(Login);
