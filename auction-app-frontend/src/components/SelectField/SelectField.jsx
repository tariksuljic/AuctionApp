import { useState, useEffect } from "react";
import { useFormContext } from "react-hook-form";

import { dropdown } from "src/assets/icons";

import "./style.scss";

const SelectField = ({ name, options, rules, label }) => {
    const { register, formState: { errors } } = useFormContext();
    
    const [selected, setSelected] = useState(null);
    const [isOpen, setIsOpen] = useState(false);

    const toggleDropdown =  () => setIsOpen(!isOpen);

    const handleSelect = (value) => {
        setSelected(value);
        setValue(name, value);
        setIsOpen(false);
    };

    useEffect(() => {
        // register field
        register(name, { ...rules });
    }, [register, name, rules]);
    

    const displayLabel = selected ? options.find(option => option.value === selected)?.label : label;

    return (
        <div className="custom-select body-regular">
            <div className="dropdown-btn" onClick={ toggleDropdown }>
                { displayLabel }
                <img src={ dropdown } alt="Dropdown" />
            </div>
            { isOpen && (
                <div className="dropdown-content">
                    { options.map(option => (
                        <div key={ option.value } className="dropdown-item" onClick={ () => handleSelect(option.value) }>
                            { option.label }
                        </div>
                    )) }
                </div>
            ) }
            { errors[name] && <span className="error-message">{ errors[name].message }</span> }
        </div>
    );
};

export default SelectField;
