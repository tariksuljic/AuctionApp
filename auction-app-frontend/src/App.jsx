import { Routes, Route } from "react-router-dom";

import { 
  Navbar, 
  Header, 
  Footer, 
  Breadcrumbs 
} from "src/components";

import {
  AboutUs,
  PrivacyPolicy,
  TermsAndConditions,
  Home,
  Shop,
  MyAccount,
  ProductDetails,
  LoginPage,
  RegisterPage
} from "src/pages";

import { BreadcrumbProvider } from "src/store/BreadcrumbContext";
import { SuggestionProvider } from "src/store/SuggestionContext";
import { WebSocketProvider } from "src/store/WebSocketContext";
import { UserProvider } from "src/store/UserContext";

import { ROUTE_PATHS } from "src/constants";

import "src/scss/index.scss";

const App = () => {
  return (
    <>
      <BreadcrumbProvider>
      <SuggestionProvider>
      <UserProvider>
      <WebSocketProvider>
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
                path={ `${ROUTE_PATHS.PRODUCT}/:id` }
                element={ <ProductDetails /> }
              />
              <Route 
                path={ ROUTE_PATHS.SHOP } 
                element={ <Shop /> } 
              />
              <Route 
                path={ ROUTE_PATHS.LOGIN } 
                element={ <LoginPage /> } 
              />
              <Route
                path={ ROUTE_PATHS.REGISTER }
                element={ <RegisterPage /> }
              />
            </Routes>
          </div>
          <Footer />
        </div>
      </WebSocketProvider>
      </UserProvider>
      </SuggestionProvider>
      </BreadcrumbProvider>
    </>
  );
};

export default App;
