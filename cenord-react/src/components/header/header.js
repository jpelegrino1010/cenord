import classes from './header.module.css';

const Header = (props) => (
  <header className={classes.header}>
    <img src="img/logo.png" className="main-logo" alt="LOGO" />
    <h1 className="title">Comunidad Noajida Luz del Caribe</h1>
  </header>
);

export default Header;
