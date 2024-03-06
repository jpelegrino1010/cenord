import { Constants } from "../constants/Constants";
import Axios from "axios";

export const getAuthentication = (user) => {
  return Axios.get(Constants.baseUrl + "/user", {
    auth: {
      username: user.username,
      password: user.password,
    },
    headers: {
      "Content-type": "Application/json",
    },
    withCredentials: true,
  });
};

export const setupAxiosInter = () => {
  const csrf = localStorage.getItem("XSRF-TOKEN");
  let basicAuthHeader = localStorage.getItem("basicHeader");
  let auth = localStorage.getItem("Authorization");
  if (auth) {
    basicAuthHeader = auth;
  }
  Axios.interceptors.request.use((config) => {
    if (isUserLogin) {
      config.headers["X-XSRF-TOKEN"] = csrf;
      config.headers.authorization = basicAuthHeader;
    } else {
      console.log(config);
    }

    return config;
  });
};

const isUserLogin = () => {
  const userAuth = localStorage.getItem("XSRF-TOKEN");
  if (userAuth) return true;
  return false;
};
