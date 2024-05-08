import { rules } from 'src/forms/rules';

export const cardInformationFields = [
    {
        label: "Name on Card",
        type: "text",
        name: "nameOnCard",
        rules: {
            ...rules.required("Name on Card"),
            ...rules.minLength("Name on Card", 2)
        }
    },
    {
        label: "Card Number",
        type: "text",
        name: "cardNumber",
        rules: rules.creditCard()
    },
    {
        label: "Expiration Date",
        type: "text",
        name: "expirationMonth",
        specialClass: "input-field-third",
        rules: rules.expirationMonth()
    },
    {
        type: "text",
        name: "expirationYear",
        specialClass: "input-field-third",
        rules: rules.expirationYear()
    },
    {
        label: "CVV",
        type: "text",
        name: "cvv",
        specialClass: "input-field-third",
        rules: rules.cvv()
    }
];
