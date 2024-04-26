import { createContext, useContext, useState, useEffect } from 'react';

const UserNameContext = createContext(null);

export const useUserName = () => useContext(UserNameContext);

export const UserNameProvider = ({ children }) => {
  const [userName, setUserName] = useState(() => {
    return localStorage.getItem('userName');
  });

  useEffect(() => {
    if (userName) {
      localStorage.setItem('userName', userName);
    } else {
      localStorage.removeItem('userName');
    }
  }, [userName]);

  return (
    <UserNameContext.Provider value={{ userName, setUserName }}>
      {children}
    </UserNameContext.Provider>
  );
};
