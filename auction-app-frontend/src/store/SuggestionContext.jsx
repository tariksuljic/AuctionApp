import { createContext, useContext, useState } from 'react';

const SuggestionContext = createContext(null);

export const useSuggestion = () => useContext(SuggestionContext);

export const SuggestionProvider = ({ children }) => {
  const [suggestion, setSuggestion] = useState(null);

  return (
    <SuggestionContext.Provider value={{ suggestion, setSuggestion }}>
      { children }
    </SuggestionContext.Provider>
  );
};
