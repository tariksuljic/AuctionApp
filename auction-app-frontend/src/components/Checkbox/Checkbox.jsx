import "./style.scss";

const Checkbox = ({ label, onChange, checked }) => {
  const handleCheckboxChange = () => {
    onChange(!checked);
  };

  return (
    <div className={ `checkbox-container ${checked ? "checked" : ""}` }>
      <label className="checkbox-label">
        <input
          type="checkbox"
          checked={ checked }
          onChange={ handleCheckboxChange }
          className="checkbox-input"
        />
        <span className="checkbox-custom" />
        { label }
      </label>
    </div>
  );
};

export default Checkbox;
