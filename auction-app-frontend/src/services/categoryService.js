import { getRequest } from "src/utils/httpUtils";

const getTopLevelCategories = () => {
  return getRequest("/categories/top-level");
};

const getCategoriesWithSubcategories = () => {
  return getRequest("/categories/with-subcategories");
};

export { getTopLevelCategories, getCategoriesWithSubcategories };
