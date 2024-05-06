import { createContext, useContext, useState, useEffect } from "react";
import { getDecodedToken } from "src/utils/jwtDecode";

const UserContext = createContext(null);

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
  const token = localStorage.getItem("accessToken");

  const [userName, setUserName] = useState(() => {
    return localStorage.getItem("userName");
  });
  const [userType, setUserType] = useState("");
  const [userId, setUserId] = useState("");

  useEffect(() => {
    if (token) {
      const decodedToken = getDecodedToken(token);
      setUserType(decodedToken?.role);
      setUserId(decodedToken?.id);
    } else {
      setUserType("");
      setUserId("");
    }
  }, [token]);

  useEffect(() => {
    if (userName) {
      localStorage.setItem("userName", userName);
    } else {
      localStorage.removeItem("userName");
    }
  }, [userName]);

  return (
    <UserContext.Provider value={{ userName, setUserName, userType, userId }}>
      {children}
    </UserContext.Provider>
  );
};
