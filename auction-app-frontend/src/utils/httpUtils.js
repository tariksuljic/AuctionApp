import axios from "axios";
import { jwtDecode } from "jwt-decode";

import { BASE_URL } from "src/constants";

const API = axios.create({
  baseURL: BASE_URL,
  withCredentials: true
});

// to check token expiration
const isTokenExpired = (token) => {
  const { exp } = jwtDecode(token);

  return Date.now() >= (exp * 1000) - (30 * 1000); // 30 seconds before expiration
};

// to refresh access token
const refreshToken = async () => {
  try {
    const response = await axios.post(`${ BASE_URL }/auth/refresh-token`, {}, { withCredentials: true });
    const newAccessToken = response.data;

    localStorage.setItem("accessToken", newAccessToken); // to local storage
    return newAccessToken; // to headers 
  } catch (error) {
    return null;
  }
};

// axios request interceptor
API.interceptors.request.use(async (config) => {
  let accessToken = localStorage.getItem("accessToken");

  if (accessToken && isTokenExpired(accessToken)) {
    const newAccessToken = await refreshToken();
    accessToken = newAccessToken || accessToken;
  }

  if (accessToken) {
    config.headers["Authorization"] = `Bearer ${ accessToken }`;
  } else {
    delete config.headers["Authorization"]; // remove invalid token
  }

  return config;
});

const getRequest = async (endpoint) => {
  const response = await API.get(endpoint);
  return response.data;
};

const postRequest = async (endpoint, body) => {
  const response = await API.post(endpoint, body);
  return response.data;
};

const putRequest = async (endpoint, body) => {
  const response = await API.put(endpoint, body);
  return response.data;
};

const deleteRequest = async (endpoint) => {
  const response = await API.delete(endpoint);
  return response.data;
};

export { getRequest, postRequest, putRequest, deleteRequest };
