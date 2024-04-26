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

export { registerUser, loginUser, logoutUser };
