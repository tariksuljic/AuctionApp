import { Routes, Route } from "react-router-dom";

import { Navbar, Header, Footer, Breadcrumbs } from "src/components";
import {
  AboutUs,
  PrivacyPolicy,
  TermsAndConditions,
  Home,
  Shop,
  MyAccount,
  ProductDetails,
} from "src/pages";

import { BreadcrumbProvider } from "src/store/BreadcrumbContext";

import { ROUTE_PATHS } from "src/constants";

import "src/scss/index.scss";

const App = () => {
  return (
    <>
      <BreadcrumbProvider>
        <Header />
        <Navbar />
        <div className="main-content">
          <div className="container">
            <Breadcrumbs />
            <Routes>
              <Route path={ ROUTE_PATHS.HOME } element={ <Home /> } />
              <Route path={ ROUTE_PATHS.SHOP } element={ <Shop /> } />
              <Route path={ ROUTE_PATHS.MY_ACCOUNT } element={ <MyAccount /> } />
              <Route path={ ROUTE_PATHS.ABOUT_US } element={ <AboutUs /> } />
              <Route
                path={ ROUTE_PATHS.PRIVACY_POLICY }
                element={ <PrivacyPolicy /> }
              />
              <Route
                path={ ROUTE_PATHS.TERMS_AND_CONDITIONS }
                element={ <TermsAndConditions /> }
              />
              <Route
                path={ `${ROUTE_PATHS.SHOP}/:id` }
                element={ <ProductDetails /> }
              />
            </Routes>
          </div>
          <Footer />
        </div>
      </BreadcrumbProvider>
    </>
  );
};

export default App;
