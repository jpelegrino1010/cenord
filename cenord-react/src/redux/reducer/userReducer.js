import * as constant from '../actions/types';

const initialState = { users: [], user: {} };

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case constant.types.LOAD_USERS_SUCCESS:
      return { ...state, users: action.users };
    case constant.types.LOAD_USER_BY_ID_SUCCESS:
      return { ...state, user: action.user };
    case constant.types.CREATE_USER_SUCCESS:
      const users = [...state.users, action.newUser];
      return { ...state, users: users };
    case constant.types.UPDATE_USER_SUCCESS:
      const modifyUsers = state.users.filter(
        (user) => user.id !== action.user.id
      );
      const updatedUsers = [...modifyUsers, action.user];
      return { ...state, users: updatedUsers };
    default:
      return state;
  }
};

export default userReducer;
