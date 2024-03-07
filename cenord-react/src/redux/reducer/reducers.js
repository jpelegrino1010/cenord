import { combineReducers } from 'redux';
import countReducer from './counterReducer';
import loginReducer from './loginReducer';
import userReducer from './userReducer';

const rootReducer = combineReducers({
  counter: countReducer,
  login: loginReducer,
  user: userReducer,
});

export default rootReducer;
