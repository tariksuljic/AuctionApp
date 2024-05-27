import { postRequest } from "src/utils/httpUtils";

const addPaymentInfo = (paymentInfo) => {
    return postRequest("/payment/stripe-payment-info", paymentInfo);
};

export { addPaymentInfo };
