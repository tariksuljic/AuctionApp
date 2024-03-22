import { SocialMediaIcons } from "src/components";

import "./style.scss";

const Header = () => {
  // hardcoded for now
  const isLoggedIn = false;
  const userName = "John Doe";

  return (
    <div className="header">
      <SocialMediaIcons />
      <div className="user-status body-small-semibold">
        {isLoggedIn ? (
          <span>Hi, {userName}</span>
        ) : (
          <div className="user-status-links">
            <a href="/login">Login</a>
            <span> or </span>
            <a href="/create-account">Create an account</a>
          </div>
        )}
      </div>
    </div>
  );
};

export default Header;
