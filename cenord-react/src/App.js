import logo from './logo.svg';
import './App.css';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Test from './components/test';
import Main from './components/main';
import Login from './components/login/Login';

const App = () => {
  const router = createBrowserRouter([
    { path: '/', element: <Login /> },
    { path: '/home', element: <Test /> },
  ]);
  return <RouterProvider router={router} />;
};

export default App;
