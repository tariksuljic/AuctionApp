import { jwtDecode } from "jwt-decode";

export const getDecodedToken = (token) => {
    try {
        const decodedToken = jwtDecode(token);

        return decodedToken;
    } catch (error) {
        return null;
    }
}
