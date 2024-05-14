import { rules } from "src/forms/rules";

export const locationShippingFormFields = [
    {
        label: "Address",
        type: "text",
        name: "address",    
        rules: {
            ...rules.required("Address"),
            ...rules.minLength(3, "Address"),
            ...rules.maxLength(100, "Address"),
            ...rules.pattern(/^[a-zA-Z0-9\s,.'-]{3,100}$/, "Invalid address")
        }
    }, 
    {
        label: "Enter email",
        type: "email",
        name: "email",
        rules: rules.email()
    },
    {
        label: "City",
        type: "text",
        name: "city",
        specialClass: "input-field-half",
        rules: {
            ...rules.required("City"),
            ...rules.minLength(3, "City"),
            ...rules.maxLength(50, "City"),
            ...rules.pattern(/^[a-zA-Z\s]{3,50}$/, "Invalid city")
        }
    }, 
    {
        label: "Zip Code",
        type: "text",
        name: "zipCode",
        specialClass: "input-field-half",
        rules: {
            ...rules.required("Zip code"),
            ...rules.minLength(5, "Zip code"),
            ...rules.maxLength(10, "Zip code"),
            ...rules.pattern(/^[0-9]{5,10}$/, "Invalid zip code")
        }
    },
    {
        label: "Country",
        type: "text",
        name: "country",
        rules: {
            ...rules.required("Country"),
            ...rules.minLength(3, "Country"),
            ...rules.maxLength(50, "Country"),
            ...rules.pattern(/^[a-zA-Z\s]{3,50}$/, "Invalid country")
        }
    }
];
