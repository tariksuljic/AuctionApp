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

const getUser = (userId) => {
    return getRequest(`/users/${userId}`);
}

const addPaymentInfoToUser = (userId, data) => {
    return postRequest(`/users/${userId}/payment-info`, data);
}

export { registerUser, loginUser, logoutUser, getUser, addPaymentInfoToUser };
