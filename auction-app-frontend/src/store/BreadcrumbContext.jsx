import { createContext, useState, useContext, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { ROUTES_MAP, HIDE_BREADCRUMBS_ON_PATHS } from "src/constants";

const BreadcrumbContext = createContext();

export const useBreadcrumb = () => useContext(BreadcrumbContext);

export const BreadcrumbProvider = ({ children }) => {
  const [title, setTitle] = useState("");
  const [breadcrumbs, setBreadcrumbs] = useState([]);
  const location = useLocation();
  const hideBreadcrumbs = HIDE_BREADCRUMBS_ON_PATHS.includes(location.pathname); // Hide breadcrumbs on certain paths

  const updateBreadcrumb = (location, title) => {
    const { pathname } = location;

    // regex to match product detail pages
    const productDetailRegex = /^\/shop\/\d+(\/)?$/;
    const isProductDetailPage = productDetailRegex.test(pathname);

    // determine the label for the current page
    const label = isProductDetailPage ? "Single Product" : ROUTES_MAP[pathname];
    // update the title for non-product detail pages
    if (!isProductDetailPage || !title) {
      setTitle(label);
    }

    // if the pathname has changed, update breadcrumbs
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
  };

  useEffect(() => {
    updateBreadcrumb(location, title);
  }, [location]);

  return (
    <BreadcrumbContext.Provider
      value={{ title, setTitle, breadcrumbs, setBreadcrumbs, hideBreadcrumbs }}
    >
      {children}
    </BreadcrumbContext.Provider>
  );
};
