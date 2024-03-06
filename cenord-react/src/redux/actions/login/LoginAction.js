import * as types from '../types';
import { getAuthentication } from '../../../services/auth-service';
import Cookies from 'js-cookie';

const loginSuccess = (user) => {
  return {
    type: types.types.LOGIN_SUCCESS,
    user,
  };
};

const loginError = () => {
  return {
    type: types.types.LOGIN_FAIL,
  };
};

export const login = (user) => {
  return async (dispatch) => {
    const login = async () => {
      const response = await getAuthentication(user);
      console.log(response.headers, response.headers.get('Authorization'));
      localStorage.setItem(
        'Authorization',
        response.headers.get('Authorization')
      );
      return response.data;
    };

    try {
      const userData = await login();

      var csrf = Cookies.get('XSRF-TOKEN');
      settingBasicHeader(user);
      localStorage.setItem('XSRF-TOKEN', csrf);
      dispatch(loginSuccess(userData));
    } catch (err) {
      dispatch(loginError());
    }
  };
};

const settingBasicHeader = (user) => {
  const basicAuthHeader =
    'Basic ' + window.btoa(`${user.username}:${user.password}`);
  localStorage.setItem('basicHeader', basicAuthHeader);
  return basicAuthHeader;
};
