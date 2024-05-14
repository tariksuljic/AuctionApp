import { getRequest } from "src/utils/httpUtils";
import { postRequest } from "../utils/httpUtils";

const getProductsByCriteria = (type, page, size) => {
  return getRequest(
    `/products/criteria?page=${ page }&size=${ size }&type=${ type }`
  );
};

const getProduct = (id) => {
  return getRequest(`/products/${ id }`);
};

const getProductRandom = () => {
  return getRequest("/products/random");
};

const getProducts = (page, size, categoryId, searchQuery) => {
  // construct query string based on the presence of categoryId or search query
  const categoryParam = categoryId ? `&category_id=${ categoryId }` : "";
  const searchParam = searchQuery
    ? `&search_product=${ encodeURIComponent(searchQuery) }`
    : "";

  return getRequest(
    `/products?page=${ page }&size=${ size }${ categoryParam }${ searchParam }`
  );
};

const getBidData = (productId) => {
  return getRequest(`/products/${ productId }/bid-details`);
};

const addProduct = (formData) => {
  return postRequest("/products", formData);
};

const findProductsByUserIdAndStatus = (userId, status, page, size) => {
  return getRequest(`/products/user-products?userId=${ userId }&status=${ status }&page=${ page }&size=${ size }`);
}

export { 
  getProducts, 
  getProduct, 
  getProductRandom, 
  getProductsByCriteria, 
  getBidData, 
  addProduct,
  findProductsByUserIdAndStatus
};
