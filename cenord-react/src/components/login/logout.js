import { connect } from "react-redux";
import {useEffect} from 'react'
import {useNavigate} from 'react-router-dom'
import * as constant from '../../redux/actions/types';


const Logout=props=> {
    const {isLogin,onLogout}=props;
    const navigate= useNavigate();

    useEffect(()=>{
        if(isLogin) {
            onLogout();
            navigate("/");
        }

        return ()=>{
            localStorage.removeItem("XSRF-TOKEN");
            localStorage.removeItem("Authorization");
            localStorage.removeItem("basicHeader");
        }
    },[isLogin])

    return;
}

const mapStateToProps=state=> {
    return {
        isLogin: state.loginReducer.isLogin || false
    }
}

const mapDispatchToProps=dispatch=> {
    return {
        onLogout:()=>dispatch({type:constant.types.LOGOUT_SUCCESS})
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Logout);