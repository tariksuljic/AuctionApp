import { createContext, useState, useContext, useEffect } from "react";
import { useLocation } from "react-router-dom";

import { MY_ACCOUNT_TABS, ROUTES_MAP, HIDE_BREADCRUMBS_ON_PATHS, SEARCH_RESULTS, ADD_ITEM_FORMS } from "src/constants";

const BreadcrumbContext = createContext();

export const useBreadcrumb = () => useContext(BreadcrumbContext);

export const BreadcrumbProvider = ({ children }) => {
  const [title, setTitle] = useState("");
  const [breadcrumbs, setBreadcrumbs] = useState([]);
  const [hideBreadcrumbs, setHideBreadcrumbs] = useState(false);

  const location = useLocation();

  const updateBreadcrumb = (location, title) => {
    const { pathname, search } = location;

    const hash = window.location.hash.replace("#", ""); // get the hash from the url
    const tab = MY_ACCOUNT_TABS.find(tab => tab.id === hash) 
                || ADD_ITEM_FORMS.find(tab => tab.id === hash) ; // check if the hash corresponds to a tab these pages

    setHideBreadcrumbs(HIDE_BREADCRUMBS_ON_PATHS.includes(pathname)); // hide breadcrumbs if the current page is in list

    let label; 

    // clear breadcrumbs if the current page is in list
    if (HIDE_BREADCRUMBS_ON_PATHS.includes(pathname)) {
      setBreadcrumbs([]);
      return; 
    }

    if (tab) {
      // hash corresponds to a tab in my account page
      label = tab.label; 

      setBreadcrumbs([
        { path: "/my-account", label: "My Account" },
        { path: `${ pathname }#${ hash }`, label }
      ]);

      setTitle(label);
    } else {
      // check if the current page is a product detail page or search page or shop page
      const productDetailRegex = /^\/product\/[\w-]+(\/)?$/;
      const isProductDetailPage = productDetailRegex.test(pathname);

      const searchParams = new URLSearchParams(search);
      const searchTerm = searchParams.get("search_product");
      const isSearchPage = searchTerm !== null;

      const isShopPage = pathname.match(/^\/shop\/[\w-]+(\/)?$/);

      if (isProductDetailPage) {
        label = "Single Product";
      } else if (isSearchPage) {
        label = `${SEARCH_RESULTS} ${searchTerm}`;
      } else if (isShopPage) {
        label = "Shop";
      } else {
        label = ROUTES_MAP[pathname] || "Unknown";
      }


      setBreadcrumbs([{ path: "/", label: "Home" }, { path: pathname, label }]);
      setTitle(label);
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
