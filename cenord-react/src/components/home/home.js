import { connect } from 'react-redux';
import {
  loadAllUsers,
  loadUserById,
  create,
  update,
} from './../../redux/actions/userAction';
import Page from '../UI/page';

const Home = (props) => {
  const loadById = () => {
    props.onGetUserById(1);
  };

  const createUser = () => {
    props.onCreateUser({
      email: 'email1@noahrd.com',
      password: 'pass123456',
      rol: { id: 1 },
    });
  };

  const updateUser = () => {
    props.onUpdateUser(16, { firstName: 'Carlito' });
  };
  return (
    <Page pageClass="content">
      Welcome to my Home page.
      <div>
        <button onClick={props.onGetAllUsers}>Load Users</button>
      </div>
      <div>
        <button onClick={loadById}>Load UserById</button>
      </div>
      <div>
        <button onClick={createUser}>create User</button>
      </div>
      <div>
        <button onClick={updateUser}>update User</button>
      </div>
    </Page>
  );
};

const mapPropsToState = (state) => {
  return {
    users: state.user.users,
  };
};

const mapDispatchToState = (dispatch) => {
  return {
    onGetAllUsers: () => dispatch(loadAllUsers()),
    onGetUserById: (userId) => dispatch(loadUserById(userId)),
    onCreateUser: (newUser) => dispatch(create(newUser)),
    onUpdateUser: (userId, user) => dispatch(update(userId, user)),
  };
};

export default connect(mapPropsToState, mapDispatchToState)(Home);
