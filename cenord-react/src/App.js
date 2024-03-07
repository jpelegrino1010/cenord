import './App.css';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import Login from './components/login/Login';
import Home from './components/home/home';

const App = () => {
  const router = createBrowserRouter([
    { path: '/', element: <Login /> },
    { path: '/home', element: <Home /> },
  ]);
  return <RouterProvider router={router} />;
};

export default App;
