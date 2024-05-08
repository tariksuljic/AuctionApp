import { useFormContext } from "react-hook-form";

import { SelectField } from "src/components"; 

import "./style.scss";

const InputField = ({ name, label, type, rules, step, options, className }) => {
    const { register, formState: { errors } } = useFormContext();

    const renderField = () => {
        const inputProps = {
            ...register(name, { ...rules }),
            id: name,
            type: type,
            className: errors[name] ? "error" : "",
            ...(step && { step }) 
        };
    
        switch (type) {
            case "select":
                return <SelectField name={ name } options={ options } rules={ rules } />;
            default:
                return <input {...inputProps} />;
        }
    };

    return (
        <div className={`${className} input-field`}>
            <label htmlFor={ name } className="body-semibold">{ label }</label>
            { renderField() }
            { errors[name] && <span className="error-message body-small-regular">{ errors[name].message }</span> }
        </div>
    );
}

export default InputField;
