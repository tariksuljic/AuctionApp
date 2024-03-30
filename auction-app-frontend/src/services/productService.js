import { getRequest } from "src/utils/httpUtils";

const getProductsPaginated = (type, page, size) => {
  return getRequest(
    `/products/criteria?page=${page}&size=${size}&type=${type}`
  );
};

const getProduct = (id) => {
  return getRequest(`/products/${id}`);
};

const getProductRandom = () => {
  return getRequest("/products/random");
};

export { getProductsPaginated, getProduct, getProductRandom };
