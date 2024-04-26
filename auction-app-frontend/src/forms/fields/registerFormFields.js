import { rules } from "src/forms/rules";

export const registerFormFields = [
  {
    label: "First Name",
    type: "text",
    name: "firstName",
    rules: {
        ...rules.required("First name"),
        ...rules.minLength(2, "First name")
    }
  },
  {
    label: "Last Name",
    type: "text",
    name: "lastName",
    rules: {
      ...rules.required("Last name"),
      ...rules.minLength(2, "Last name")
    }
  },
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
