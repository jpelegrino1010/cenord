import { combineReducers } from "redux";
import countReducer from "./counterReducer";
import loginReducer from "./loginReducer";

const rootReducer = combineReducers({
  counter: countReducer,
  login: loginReducer,
});

export default rootReducer;
