import { useState } from "react";
import { useStripe, useElements, CardNumberElement, CardExpiryElement, CardCvcElement } from "@stripe/react-stripe-js";

import { Button, ErrorComponent, ButtonLoadingIndicator } from "src/components";
import { BUTTON_VARIANTS, BUTTON_LABELS } from "src/constants";

import { validateCardholderName } from "src/forms/rules/validationRules";

import "./style.scss";

const CheckoutPaymentForm = ({ clientSecret, onPaymentSuccess }) => {
    const [errors, setErrors] = useState({
        stripe: "",
        cardNumber: "",
        cardExpiry: "",
        cardCvc: "",
        cardholderName: ""
    });
    const [successMessage, setSuccessMessage] = useState("");
    const [cardholderName, setCardholderName] = useState("");
    const [loading, setLoading] = useState(false);

    const stripe = useStripe();
    const elements = useElements();

    const handleSubmit = async (event) => {
        event.preventDefault();

        setLoading(true);

        const nameError = validateCardholderName(cardholderName);

        if (nameError) {
            setErrors(prevErrors => ({ ...prevErrors, cardholderName: nameError }));
            setLoading(false);

            return;
        }

        if (!stripe || !elements) {
            setErrors(prevErrors => ({ ...prevErrors, stripe: "Stripe has not initialized" }));
            setLoading(false);

            return;
        }

        const cardElement = elements.getElement(CardNumberElement);

        if (!cardElement) {
            setErrors(prevErrors => ({ ...prevErrors, stripe: "Card details not available" }));
            setLoading(false);

            return;
        }

        try {
            const { token } = await stripe.createToken(cardElement, { name: cardholderName });

            setSuccessMessage("Payment confirmed successfully");
            onPaymentSuccess(token);
        } catch (error) {
            setErrors(prevErrors => ({ ...prevErrors, stripe: error.message || "An error occurred during payment confirmation." }));
        } finally {
            setLoading(false);
        }
    };

    const handleChange = (type) => (event) => {
        setErrors(prevErrors => ({ ...prevErrors, [type]: event.error ? event.error.message : "" }));
    };

    const handleNameChange = (event) => {
        const newName = event.target.value;
        
        setCardholderName(newName);
        setErrors(prevErrors => ({ ...prevErrors, cardholderName: validateCardholderName(newName) }));
    };

    return (
        <div className="form-container">
            <form onSubmit={ handleSubmit }>
                <label className="body-regular">
                    Name on Card
                    <div className="input-container">
                        <input
                            type="text"
                            value={ cardholderName }
                            onChange={ handleNameChange }
                            placeholder="Name on Card"
                        />
                    </div>
                    { errors.cardholderName && <div className="error-message">{ errors.cardholderName }</div> }
                </label>
                <label className="body-regular">
                    Card Number
                    <div className="input-container">
                        <CardNumberElement
                            onChange={ handleChange("cardNumber") }
                        />
                    </div>
                    { errors.cardNumber && <div className="error-message">{ errors.cardNumber }</div> }
                </label>
                <div className="form-row">
                    <label className="body-regular">
                        Expiration Date
                        <div className="input-container">
                            <CardExpiryElement
                                onChange={ handleChange("cardExpiry") }
                            />
                        </div>
                        { errors.cardExpiry && <div className="error-message">{ errors.cardExpiry }</div> }
                    </label>
                    <label className="body-regular">
                        CVC
                        <div className="input-container">
                            <CardCvcElement
                                onChange={ handleChange("cardCvc") }
                            />
                        </div>
                        { errors.cardCvc && <div className="error-message">{ errors.cardCvc }</div> }
                    </label>
                </div>
                <div className="button-container">
                    <Button
                        variant={ BUTTON_VARIANTS.FILLED }
                        label = { loading ? <ButtonLoadingIndicator /> : BUTTON_LABELS.PAY }
                        disabled={ !stripe || !elements || loading }
                        type="submit"
                    />
                </div>
            </form>
        </div>
    );
};

export default CheckoutPaymentForm;
