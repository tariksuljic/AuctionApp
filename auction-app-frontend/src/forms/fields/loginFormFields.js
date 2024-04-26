import { rules } from 'src/forms/rules';

export const loginFormFields = [
    {
        label: "Email",
        type: "email",
        name: "email",
        rules: rules.email()
    },
    {
        label: "Password",
        type: "password",
        name: "password",
        rules: rules.password()
    }
];
