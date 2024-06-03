import React, { useState, useEffect } from "react";
import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { useNavigate } from "react-router-dom";

import { CheckoutAddressForm, CheckoutPaymentForm, LoadingComponent, ErrorComponent } from "src/components";
import { STRIPE_PUBLIC_KEY, CHECKOUT_STEPS, ROUTE_PATHS } from "src/constants";
import { createPaymentIntent } from "src/services";
import { addPaymentInfo } from "src/services/paymentService";
import { useUser } from "src/store/UserContext";
import { getUser } from "src/services/userService";

import "./style.scss";

const stripePromise = loadStripe(STRIPE_PUBLIC_KEY);

const CheckoutComponent = ({ product }) => {
    const [step, setStep] = useState(CHECKOUT_STEPS.ADDRESS);
    const [clientSecret, setClientSecret] = useState();
    const [loading, setLoading] = useState();
    const [errorMessage, setErrorMessage] = useState();
    const [addressInformation, setAddressInformation] = useState({});
    const [userData, setUserData] = useState(null);
    const [successMessage, setSuccessMessage] = useState(null);

    const navigate = useNavigate();

    const { userName, email, userId } = useUser();

    const getInitialData = () => {
        if (!userId) return;
    
        setLoading(true);
        
        getUser(userId)
            .then(response => {
                setUserData(response || {});
                setLoading(false);
            })
            .catch(error => {
                setErrorMessage(error.message);
                setLoading(false);
                setUserData({});
            });
    };

    useEffect(() => {
        getInitialData();
    }, [userId]);

    const onAddressFormSubmit = (addressData) => {
        setAddressInformation(addressData);
        setStep(CHECKOUT_STEPS.PAYMENT);
    };

    const onPaymentSuccess = (token) => {
        const paymentInfo = {
            ...addressInformation,
            stripeToken: token.id
        };
    
        addPaymentInfo(paymentInfo).then(() => {
            setSuccessMessage("Your transaction has been successfully completed.");

            setTimeout(() => {
                navigate(ROUTE_PATHS.HOME);
            }, 5000);

        }).catch((error) => {
            setErrorMessage("Failed to process payment info. Please try again.");
        });
    };

    const fetchPaymentIntent = async () => {
        setLoading(true);

        try {
            const response = await createPaymentIntent({
                customerEmail: email,
                customerName: userName,
                product: product
            });

            if (response.clientSecret) {
                setClientSecret(response.clientSecret);
            }

            setLoading(false);
        } catch (error) {
            setErrorMessage(error.message);
            setLoading(false);
        }
    };

    useEffect(() => {
        if (step === CHECKOUT_STEPS.PAYMENT) {
            fetchPaymentIntent();
        }
    }, [step]);

    if (loading) return <LoadingComponent />;
    if (errorMessage) return <ErrorComponent message={ errorMessage } />;

    return (
        <div className="checkout-container">
            { CHECKOUT_STEPS.ADDRESS === step && (
                <CheckoutAddressForm 
                    onAddressFormSubmit={ onAddressFormSubmit } 
                    initialData={ userData ? userData.paymentInfoEntity : {} }
                />
            ) }
            { CHECKOUT_STEPS.PAYMENT === step && clientSecret && (
                <Elements stripe={ stripePromise } options={{ clientSecret }}>
                    <CheckoutPaymentForm 
                        clientSecret={ clientSecret } 
                        onPaymentSuccess={ onPaymentSuccess } 
                    />
                </Elements>
            ) }
            { successMessage && <div className="success-message body-bold">{ successMessage }</div> }
        </div>
    );
};

export default CheckoutComponent;
