import { postRequest } from "src/utils/httpUtils";

const createPaymentIntent = (chargeRequest) => {
    return postRequest("/stripe/checkout", chargeRequest);
};

export { createPaymentIntent };
