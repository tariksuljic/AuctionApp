import { useFormContext } from "react-hook-form";

import "./style.scss"

const InputField = ({ name, label, type, rules, step }) => {
    const { register, formState: { errors } } = useFormContext(); 

    const inputProps = {
        ...register(name, { ...rules }),
        id: name,
        type: type,
        className: errors[name] ? "error" : "",
        ...(step && { step })
    };

    return (
        <div className="input-field">
            <label htmlFor={ name } className="body-semibold">{ label }</label>
            <input { ...inputProps } />
            { errors[name] && <span className="error-message body-small-regular">{ errors[name].message }</span> }
        </div>
    );
}

export default InputField;
