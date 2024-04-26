import { useFormContext } from 'react-hook-form';

import "./style.scss"

const InputField = ({ name, label, type, rules }) => {
    const { register, formState: { errors } } = useFormContext(); 

    return (
        <div className="input-field">
            <label htmlFor={ name } className="body-semibold">{ label }</label>
            <input
                id={ name }
                type={ type }
                { ...register(name, { ...rules }) }
                className={ errors[name] ? 'error' : '' }
            />
            { errors[name] && <span className="error-message body-small-regular">{ errors[name].message }</span> }
        </div>
    );
}

export default InputField;
