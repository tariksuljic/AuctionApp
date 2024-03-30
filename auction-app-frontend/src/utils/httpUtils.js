import axios from "axios";

import { BASE_URL } from "src/constants";

const API = axios.create({ baseURL: BASE_URL });

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
