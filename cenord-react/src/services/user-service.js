import { Constants } from '../constants/Constants';
import Axios from 'axios';
import { setupAxiosInter } from './auth-service';

export const getAllUsers = () => {
  setupAxiosInter();
  return Axios.get(`${Constants.baseUrl}/api/v1/members`);
};

export const getUserById = (userId) => {
  setupAxiosInter();
  return Axios.get(`${Constants.baseUrl}/api/v1/members/${userId}`, {
    headers: {
      'Content-type': 'Application/json',
    },
  });
};

export const createUser = (newUser) => {
  setupAxiosInter();
  const user = {
    email: newUser.email,
    password: newUser.password,
    rol: newUser.rol,
    firstName: newUser.firstName,
    lastName: newUser.lastName,
  };
  return Axios.post(`${Constants.baseUrl}/api/v1/members`, user, {
    headers: {
      'Content-type': 'Application/json',
    },
  });
};

export const updateUser = (userId, updateUser) => {
  setupAxiosInter();

  return Axios.put(
    `${Constants.baseUrl}/api/v1/members/${userId}`,
    updateUser,
    {
      headers: {
        'Content-type': 'Application/json',
      },
    }
  );
};
