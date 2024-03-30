import { getRequest } from "src/utils/httpUtils";

const getTopLevelCategories = () => {
  return getRequest("/categories/top-level");
};

export { getTopLevelCategories };
