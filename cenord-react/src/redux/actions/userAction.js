import * as constant from '../actions/types';
import {
  getAllUsers,
  getUserById,
  createUser,
  updateUser,
} from './../../services/user-service';

const loadUsersSuccess = (users) => {
  return {
    type: constant.types.LOAD_USERS_SUCCESS,
    users,
  };
};

const loadUserByIdSuccess = (user) => {
  return {
    type: constant.types.LOAD_USER_BY_ID_SUCCESS,
    user,
  };
};

const createUserSuccess = (user) => {
  return {
    type: constant.types.CREATE_USER_SUCCESS,
    user,
  };
};

const updateUserSuccess = (user) => {
  return {
    type: constant.types.UPDATE_USER_SUCCESS,
    user,
  };
};

export const loadAllUsers = () => {
  return async (dispatch) => {
    const allUsers = async () => {
      const response = await getAllUsers();
      return response.data;
    };

    try {
      const users = await allUsers();
      dispatch(loadUsersSuccess(users));
    } catch (err) {
      console.error(err);
    }
  };
};

export const loadUserById = (userId) => {
  return async (dispatch) => {
    const loadUser = async () => {
      const response = await getUserById(userId);
      return response.data;
    };

    try {
      const user = await loadUser();
      dispatch(loadUserById(user));
    } catch (err) {
      console.error(err);
    }
  };
};

export const create = (newUser) => {
  return async (dispatch) => {
    const save = async () => {
      const response = await createUser(newUser);
      return response.data;
    };

    try {
      const user = await save();
      dispatch(createUserSuccess(user));
    } catch (err) {
      console.error(err);
    }
  };
};

export const update = (userId, userModify) => {
  return async (dispatch) => {
    const save = async () => {
      const response = await updateUser(userId, userModify);
      return response.data;
    };

    try {
      const user = await save();
      dispatch(updateUserSuccess(user));
    } catch (err) {
      console.error(err);
    }
  };
};
