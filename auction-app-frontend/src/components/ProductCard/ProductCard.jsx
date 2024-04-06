import { Link } from "react-router-dom";

import { ROUTE_PATHS } from "src/constants";

import "./style.scss";

const ProductCard = ({ id, productImages, name, startPrice }) => {
  return (
    <div className="product-card">
      <Link to={ `${ROUTE_PATHS.PRODUCT}/${id}` }>
        <div className="img-container">
          <img src={ productImages[0].imageUrl } alt={ name } />
        </div>
      </Link>
      <div className="product-info">
        <h5>{ name }</h5>
        <span className="body-medium">
          Start From <span className="body-bold">${ startPrice }</span>
        </span>
      </div>
    </div>
  );
};

export default ProductCard;
