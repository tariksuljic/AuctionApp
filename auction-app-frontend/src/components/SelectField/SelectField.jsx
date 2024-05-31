import { useState, useEffect } from "react";
import { useFormContext } from "react-hook-form";

import { dropdown } from "src/assets/icons";

import "./style.scss";

const SelectField = ({ name, options = [], rules, label, onSelectChange, className, useForm = true, defaultValue }) => {
    const formContext = useForm ? useFormContext() : null;
    const { register, setValue, watch, formState } = formContext || {};

    useEffect(() => {
        if (useForm) {
            register(name, rules);
        }
    }, [register, name, rules, useForm]);

    const currentValue = useForm ? watch(name) : undefined;
    const [selected, setSelected] = useState(defaultValue || (useForm ? watch(name) : undefined));
    const [isOpen, setIsOpen] = useState(false);

    const toggleDropdown = () => setIsOpen(!isOpen);

    const handleSelect = (value) => {
        setSelected(value);
        if (useForm) {
            setValue(name, value, { shouldValidate: true });
        }
        setIsOpen(false);
        if (onSelectChange) {
            onSelectChange(value);
        }
    };

    useEffect(() => {
        if (currentValue) setSelected(currentValue);
    }, [currentValue]);

    const displayLabel = selected ? options.find(option => option.value === selected)?.label : label;

    return (
        <div className={ `${ className } input-field` }>
            <div className="custom-select body-regular">
                <div className="dropdown-btn" onClick={ toggleDropdown }>
                    { displayLabel }
                    <img src={ dropdown } alt="Dropdown" />
                </div>
                { isOpen && (
                    <div className="dropdown-content">
                        { options.map((option) => (
                            <div key={ option.value } className="dropdown-item" onClick={ () => handleSelect(option.value) }>
                                { option.label }
                            </div>
                        )) }
                    </div>
                ) }
            </div>
            { useForm && errors[name] && <span className="error-message body-small-regular">{ errors[name].message }</span> }
        </div>
    );
};

export default SelectField;
