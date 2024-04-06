import { createContext, useState, useContext, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { ROUTES_MAP, HIDE_BREADCRUMBS_ON_PATHS, SEARCH_RESULTS } from "src/constants";

const BreadcrumbContext = createContext();

export const useBreadcrumb = () => useContext(BreadcrumbContext);

export const BreadcrumbProvider = ({ children }) => {
  const [title, setTitle] = useState("");
  const [breadcrumbs, setBreadcrumbs] = useState([]);

  const location = useLocation();

  // regex to match shop page with categories ids
  const shopPageRegex = /^\/shop\/[\w-]+(\/)?$/;
  const isShopPage = shopPageRegex.test(location.pathname);

  // also hide breadcrumbs from the list
  const hideBreadcrumbs =
    HIDE_BREADCRUMBS_ON_PATHS.includes(location.pathname) || isShopPage;

  const updateBreadcrumb = (location, title) => {
    const { pathname, search } = location;

    // regex to match product detail pages
    const productDetailRegex = /^\/product\/[\w-]+(\/)?$/;
    const isProductDetailPage = productDetailRegex.test(pathname);

    // get the search term from the URL if it exists
    const searchParams = new URLSearchParams(search);
    const searchTerm = searchParams.get("search_product");

    const isSearchPage = searchTerm !== null;

    // determine the label for the current page
    let label; 

    if(isProductDetailPage) {
      label = "Single Product";
    } else if(isSearchPage) {
      label = `${SEARCH_RESULTS} ${searchTerm}`
    } else if(isShopPage) {
      label = "Shop";
    } else {
      label = ROUTES_MAP[pathname];
    }

    // update the title for non-product detail pages
    if (!isProductDetailPage || !title) {
      setTitle(label);
    }

    if (isSearchPage) {
      setBreadcrumbs([{ path: "/", label: "Home" }, { path: pathname, label: label }]);
    } else {
      setBreadcrumbs((prev) => {
        // check if the previous page is the same as the current one to avoid duplication
        if (prev.length && prev[prev.length - 1].path === pathname) {
          // if already on the current page, don't add to breadcrumbs
          return prev;
        } else {
          const newBreadcrumbs = prev.length
            ? [prev[prev.length - 1], { path: pathname, label: label }]
            : [{ path: pathname, label: label }];
          return newBreadcrumbs;
          }
        });
      }
  };

  useEffect(() => {
    updateBreadcrumb(location, title);
  }, [location]);

  return (
    <BreadcrumbContext.Provider
      value={{ title, setTitle, breadcrumbs, setBreadcrumbs, hideBreadcrumbs }}
    >
      { children }
    </BreadcrumbContext.Provider>
  );
};
