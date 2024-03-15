import classes from './navigation.module.css';

const Navigation = (props) => (
  <nav className={classes['main-nav']}>
    <ul className={classes['main-nav-list']}>
      <li>
        <a className={classes['main-nav-link']} href="#">
          LINK 1
        </a>
      </li>

      <li>
        <a className={classes['main-nav-link']} href="#">
          LINK 2
        </a>
      </li>

      <li>
        <a className={classes['main-nav-link']} href="#">
          LINK 3
        </a>
      </li>

      <li>
        <a className={classes['main-nav-link']} href="#">
          LINK 4
        </a>
      </li>
    </ul>
  </nav>
);

export default Navigation;
