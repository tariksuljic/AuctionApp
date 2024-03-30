import "./style.scss";

const ProductCard = ({ imageUrl, name, startPrice }) => {
  return (
    <div className="product-card">
      <div className="img-container">
        <img src={imageUrl} alt={name} />
      </div>
      <div className="product-info">
        <h5>{name}</h5>
        <span className="body-medium">
          Start From <span className="body-bold">${startPrice}</span>
        </span>
      </div>
    </div>
  );
};

export default ProductCard;
