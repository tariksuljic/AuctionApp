import { useNavigate } from "react-router-dom";

import { Button } from "src/components";

import { BUTTON_VARIANTS, BUTTON_LABELS, ROUTE_PATHS } from "src/constants";

import "./style.scss"; 

const ProductBidsItem = ({ 
  id,
  imgSrc, 
  title, 
  timeLeft, 
  bidPrice, 
  highestBid, 
  noBids, 
  buttonLabel, 
  onButtonClick, 
  highestBidder,
  auctionEnded
}) => {
  const navigate = useNavigate();

  const userIsHighestBidder = bidPrice === highestBid;

  const onPayButtonClick = () => {
    navigate(`${ ROUTE_PATHS.PRODUCT }/${ id }`);
  }

  return (
    <div className="product-bid-item">
      <div className="item-image">
        <img src={ imgSrc } alt={ title } />
      </div>
      <div className="item-title">{ title }</div>
      <div className="item-time-left">{ timeLeft }</div>
      <div className={ `${ highestBidder ? "highestBid" : "lowerBid" } item-your-price` }>${ bidPrice }</div>
      <div className="item-no-bids"> { noBids } </div>
      <div className="item-highest-bid">${ highestBid }</div>
      <div className="item-actions">
        { auctionEnded && userIsHighestBidder ? (
          <Button 
            variant={ BUTTON_VARIANTS.OUTLINED } 
            label={ BUTTON_LABELS.PAY }
            onButtonClick={ onPayButtonClick }
          />
        ) : (
          <Button 
            variant={ BUTTON_VARIANTS.OUTLINED } 
            label={ buttonLabel } 
            onButtonClick={ onButtonClick }
          />
        ) }
      </div>
    </div>
  );
}

export default ProductBidsItem;
