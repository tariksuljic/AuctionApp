import { postRequest, getRequest } from "src/utils/httpUtils";

const registerUser = (data) => {
    return postRequest("/auth/register", data);
}

const loginUser = (data) => {
    return postRequest("/auth/login", data);
}

const logoutUser = () => {
    return getRequest("/auth/logout");
}

const getPaymentInfoByUser = (userId) => {
    return getRequest(`/users/${userId}/payment-info`);
}

const addPaymentInfoToUser = (userId, data) => {
    return postRequest(`/users/${userId}/payment-info`, data);
}

export { registerUser, loginUser, logoutUser, getPaymentInfoByUser, addPaymentInfoToUser };
