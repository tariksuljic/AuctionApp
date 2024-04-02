import { NavLink, Link } from "react-router-dom";

import { NAV_ITEMS } from "src/constants";
import { logo } from "src/assets/images";

import "./style.scss";

const Navbar = () => {
  return (
    <div className="navbar">
      <div className="app-logo">
        <Link to="/">
          <img src={ logo } alt="Auction App Logo" />
        </Link>
      </div>
      <div className="navbar-items body-regular">
        { NAV_ITEMS.map((item) => (
          <NavLink
            to={ item.link }
            className={ ({ isActive }) =>
              isActive ? "navbar-item active" : "navbar-item"
            }
            key={ item.key }
          >
            { item.label }
          </NavLink>
        )) }
      </div>
    </div>
  );
};

export default Navbar;
