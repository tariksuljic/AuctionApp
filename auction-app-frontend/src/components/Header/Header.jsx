import { Link, useNavigate } from "react-router-dom";

import { SocialMediaIcons } from "src/components";

import { ROUTE_PATHS } from "src/constants";
import { logoutUser } from "src/services/userService";
import { useUser } from "src/store/UserContext";

import "./style.scss";

const Header = () => {
  const { userName, setUserName } = useUser();
  const navigate = useNavigate();

  const onLogout = () => {
    setUserName(null);

    localStorage.removeItem('userName');
    localStorage.removeItem('accessToken');
    
    logoutUser();
    navigate(ROUTE_PATHS.HOME);
  }
  
  return (
    <div className="header">
      <SocialMediaIcons />
      <div className="user-status body-small-semibold">
        { userName ? (
          <>
            <span>Hi, { userName }</span>
            <span className="logout" onClick={ onLogout }> Logout </span>
          </>
          ) : (
          <div className="user-status-links">
            <Link to={ ROUTE_PATHS.LOGIN }>
              <span>Login</span>
            </Link>
            <span className="span-connect"> or </span>
            <Link to={ ROUTE_PATHS.REGISTER }>
              <span>Create an account</span>
            </Link>
          </div>
        ) }
      </div>
    </div>
  );
};

export default Header;
