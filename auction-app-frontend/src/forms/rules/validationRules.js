export const rules = {
    required: (field) => ({
      required: `${field} is required`
    }),
    minLength: (minLength, field) => ({
        minLength: {
            value: minLength,
            message: `${field} must be at least ${minLength} characters long`
        }
    }),
    maxLength: (maxLength, field) => ({
        maxLength: {
            value: maxLength,
            message: `${field} must be at most ${maxLength} characters long`
        }
    }),
    minValue: (minValue, field) => ({
        min: {
            value: minValue,
            message: `${field} must be at least $${minValue}`
        }
    }),
    maxValue: (maxValue, field) => ({
        max: {
            value: maxValue,
            message: `${field} must be at most $${maxValue}`
        }
    }),
    pattern: (pattern, message) => ({
        pattern: {
            value: pattern,
            message: message
        }
    }),
    email: () => ({
        ...rules.required("Email"),
        ...rules.pattern(/^\S+@\S+\.\S+$/, "Please enter a valid email")
    }),
    password: () => ({
        ...rules.required("Password"),
        ...rules.minLength(8, "Password"),
        ...rules.pattern(
            /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}$/, 
            "Password must contain at least one uppercase letter, one lowercase letter, and one number"
        )
    }), 
    creditCard: () => ({
        ...rules.required("Credit Card"),
        ...rules.pattern(/^\d{16}$/, "Enter a valid 16-digit number"),
        validate: (value) => checkCardNumberValidity(value) || "Enter a valid credit card number"
    }),
    expirationYear: () => ({
        ...rules.required("Year"),
        ...rules.pattern(/^\d{2}$/, "Enter a valid year (YY)"),
        validate: (value) => validateYear(value) || "Year cannot be in the past"
    }),
    expirationMonth: () => ({
        ...rules.required("Month"),
        ...rules.pattern(/^(0[1-9]|1[0-2])$/, "Enter a valid month (MM)")
    }),
    cvv: () => ({
        ...rules.required("CVV"),
        ...rules.minLength(3, "CVV"),
        ...rules.maxLength(4, "CVV"),
        ...rules.pattern(/^\d{3,4}$/, "CVV must be 3 or 4 digits")
    }),        
    street: () => ({
        ...rules.required("Street"),
        ...rules.minLength(3, "Street"),
        ...rules.maxLength(100, "Street"),
        ...rules.pattern(/^[a-zA-Z0-9\s,."-]{3,100}$/, "Invalid street name")
    }),
    city: () => ({
        ...rules.required("City"),
        ...rules.minLength(2, "City"),
        ...rules.maxLength(50, "City"),
        ...rules.pattern(/^[a-zA-Z\s]{2,50}$/, "Invalid city name")
    }),
    state: () => ({
        ...rules.required("State"),
        ...rules.minLength(2, "State"),
        ...rules.maxLength(50, "State"),
        ...rules.pattern(/^[a-zA-Z\s]{2,50}$/, "Invalid state name")
    }),
    country: () => ({
        ...rules.required("Country"),
        ...rules.minLength(2, "Country"),
        ...rules.maxLength(50, "Country"),
        ...rules.pattern(/^[a-zA-Z\s]{2,50}$/, "Invalid country name")
    }),
    month: () => ({
        ...rules.required("Month"),
        ...rules.minValue(1, "Month"),
        ...rules.maxValue(12, "Month"),
        ...rules.pattern(/^[0-9]{1,2}$/, "Invalid month")
    }),
    day: () => ({
        ...rules.required("Day"),
        ...rules.minValue(1, "Day"),
        ...rules.maxValue(31, "Day"),
        ...rules.pattern(/^[0-9]{1,2}$/, "Invalid day")
    }),
    year: () => ({
        ...rules.required("Year"),
        ...rules.minValue(1930, "Year"),
        ...rules.maxValue(2024, "Year"),
        ...rules.pattern(/^[0-9]{2,4}$/, "Invalid year")
    })
};

const checkCardNumberValidity = (cardNo) => {
    let s = 0;
    let doubleDigit = false;

    for (let i = cardNo.length - 1; i >= 0; i--) {
        let digit = +cardNo[i];

        if (doubleDigit) {
            digit *= 2;

            if (digit > 9)
                digit -= 9;
        }

        s += digit;
        doubleDigit = !doubleDigit;
    }

    return s % 10 == 0;
};

const validateYear = (value) => {
    const currentYear = new Date().getFullYear();
    const fullYear = parseInt("20" + value, 10);

    return fullYear >= currentYear;
};
