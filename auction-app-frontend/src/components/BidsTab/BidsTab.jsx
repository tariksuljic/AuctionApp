import { useState, useEffect } from "react";

import { ProductBidsTable, ErrorComponent, Button } from "src/components";

import { useUser } from "src/store/UserContext";
import { findBidsByUserId } from "src/services/bidService";

import { BUTTON_LABELS, MY_ACCOUNT_TABS_MAP, BIDS_DEFAULT_PAGE_NUMBER, BUTTON_VARIANTS } from "src/constants";

import "./style.scss";

const BidsTab = () => {
  const [bids, setBids] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);

  const { userId } = useUser(); 

  const fetchBids = (page) => {
    if (!userId) return;

    setLoading(true);
    
    findBidsByUserId(userId, page, BIDS_DEFAULT_PAGE_NUMBER)
      .then((bids) => {
        setBids((prevItems) =>
          page === 0
            ? [...bids.content]
            : [...prevItems, ...bids.content]
        );

        setHasMore(bids.last !== true);
      })
      .catch((error) => {
        setError(error.message);
      })
      .finally(() => {
        setLoading(false);
      });
  }

  useEffect(() => {
    fetchBids(page);
  }, [userId, page]);

  const handlePageChange = (page) => {
    setPage((prevPage) => prevPage + 1);
  }

  if (error) return <ErrorComponent message={ error } />;

  return (
      <div className="bids-tab-container">
          <ProductBidsTable 
            items = { bids } 
            buttonLabel = { BUTTON_LABELS.BID } 
            tabId = { MY_ACCOUNT_TABS_MAP.BIDS }
          /> 
          { hasMore && (
            <div className="load-more-btn">
              <Button 
                label = { BUTTON_LABELS.LOAD_MORE } 
                onButtonClick = { () => handlePageChange(page) } 
                variant = { BUTTON_VARIANTS.FILLED }
                className = "load-more-button"
              />
            </div>
          ) }
      </div>
  )
}

export default BidsTab;
