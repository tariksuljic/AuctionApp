import { NavLink, Link, useLocation } from "react-router-dom";

import { Searchbar, SuggestionBox } from "src/components";

import { NAV_ITEMS, HIDE_NAV_OPTIONS_ON_PATHS } from "src/constants";
import { logo } from "src/assets/images";

import "./style.scss";

const Navbar = () => {
  const location = useLocation();

  const shouldDisplaySuggestionBox = location.pathname.startsWith('/shop'); // only show suggestion box in shop page

  const shouldHideOptions = (HIDE_NAV_OPTIONS_ON_PATHS.includes(location.pathname)) ? true : "";

  return (
    <>
      <div className={ `navbar ${shouldHideOptions ? 'center-logo' : ''}` }>
        <div className="app-logo">
          <Link to="/">
            <img src={ logo } alt="Auction App Logo" />
          </Link>
        </div>
        { !shouldHideOptions && (
          <>
            <Searchbar />
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
          </>
        ) }
      </div>
      { shouldDisplaySuggestionBox && <SuggestionBox /> }
    </>
  );
};

export default Navbar;
