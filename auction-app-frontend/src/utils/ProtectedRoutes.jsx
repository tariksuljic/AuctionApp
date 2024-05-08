import { Navigate, Outlet, useLocation } from "react-router-dom";

import { ROUTE_PATHS } from "src/constants";

const ProtectedRoutes = ({ children }) => {
    const location = useLocation();

    const accessToken = localStorage.getItem("accessToken");

    if (accessToken === null) {
        return <Navigate to={ ROUTE_PATHS.LOGIN } state={ { from: location } } />;
    }

  return <Outlet />; // return child route elements
};

export default ProtectedRoutes;
