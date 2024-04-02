import { Link } from "react-router-dom";

import { facebook, instagram, twitter } from "src/assets/icons";

import "./style.scss";

const SocialMediaIcons = () => {
  return (
    <div className="social-media-icons">
      <Link to="https://www.facebook.com/">
        <img src={ facebook } alt="facebook" />
      </Link>
      <Link to="https://www.instagram.com/">
        <img src={ instagram } alt="instagram" />
      </Link>
      <Link to="https://www.twitter.com/">
        <img src={ twitter } alt="twitter" />
      </Link>
    </div>
  );
};

export default SocialMediaIcons;
