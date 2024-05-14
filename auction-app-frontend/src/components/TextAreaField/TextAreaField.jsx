import { useFormContext } from "react-hook-form";

const TextAreaField = ({ name, label, rules, className, placeholder }) => {
    const { register, formState: { errors } } = useFormContext();

    return (
        <div className={ `${className} input-field` }>
            <label htmlFor={name} className="body-semibold">{ label }</label>
            <textarea { ...register(name, { ...rules }) } placeholder={ placeholder } className={ errors[name] ? "error" : "" } />
            { errors[name] && <span className="error-message body-small-regular">{ errors[name].message }</span> }
        </div>
    );
};

export default TextAreaField;
