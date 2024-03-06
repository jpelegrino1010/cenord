import * as constant from '../actions/types';

const initialState={
    isLogin:false,
    user:{}
};

const loginReducer=(state=initialState,action)=> {

   switch(action.type) {
        case constant.types.LOGIN_SUCCESS:
        return {
            ...state,isLogin:true,user:action.user
        }
        case constant.types.LOGIN_FAIL:            
        case constant.types.LOGOUT_SUCCESS:
            return {...state,isLogin:false}

        default:
            return state;
    }

    
}

export default loginReducer;