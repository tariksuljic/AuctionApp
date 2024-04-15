import { useNavigate, useLocation } from "react-router-dom";

import { useSuggestion } from "src/store/SuggestionContext";

import "./style.scss"

const SuggestionBox = () => {
  const { suggestion, setSuggestion } = useSuggestion();
  
  const navigate = useNavigate();

  if (!suggestion) return null; // dont render if there is no suggestion

  const handleSuggestionClick = () => {
    const currentSearchParams = new URLSearchParams(location.search);
    currentSearchParams.set("search_product", suggestion);

    setSuggestion(null);

    navigate(`/shop?${currentSearchParams.toString()}`);
  };

  return (
    <div className="suggestion-box body-regular">
      <span> Did you mean? </span>
      <span className="suggestion" onClick={ handleSuggestionClick }>
        { suggestion }
      </span>
    </div>
  );
};

export default SuggestionBox;
