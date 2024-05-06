import { BUTTON_VARIANTS } from "src/constants";

import "./style.scss";

const Button = ({ label, iconSrc, disabled = false, variant = BUTTON_VARIANTS.OUTLINED, onButtonClick }) => {
  const buttonClasses = `btn body-bold ${ disabled ? "disabled" : "" } ${
    variant === BUTTON_VARIANTS.FILLED ? "filled" : ""
  }`;

  return (
    <button className={ buttonClasses } disabled={ disabled } onClick={ onButtonClick }>
      { label }
      { iconSrc && <img src={ iconSrc } alt="icon" className="btn-icon" /> }
    </button>
  );
};

export default Button;
