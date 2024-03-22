import { Link } from "react-router-dom";

import { SocialMediaIcons } from "src/components";

import "./style.scss";

const Footer = () => {
  return (
    <div className="footer">
      <div className="footer-column body-regular">
        <div className="column-heading">
          <span>Auction</span>
        </div>
        <div className="column-content">
          <Link to="/about-us">About Us</Link>
          <Link to="/terms-and-conditions">Terms And Conditions</Link>
          <Link to="/privacy-policy">Privacy Policy</Link>
        </div>
      </div>

      <div className="footer-column body-regular">
        <div className="column-heading">
          <span>Get in touch</span>
        </div>
        <div className="column-content">
          <span>Call Us at +123 797-567-2535</span>
          <span>support@auction.com</span>
          <SocialMediaIcons />
        </div>
      </div>
    </div>
  );
};

export default Footer;
