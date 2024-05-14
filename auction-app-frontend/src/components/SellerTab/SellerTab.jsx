import { useState, useEffect } from "react";
import { ProductBidsTable, ErrorComponent, Button } from "src/components";

import { 
  SELLER_TABS, 
  MY_ACCOUNT_TABS_MAP, 
  BIDS_DEFAULT_PAGE_NUMBER, 
  BUTTON_LABELS, 
  BUTTON_VARIANTS,
  PRODUCT_STATUS
} from "src/constants";
import { findProductsByUserIdAndStatus } from "src/services";
import { useUser } from "src/store/UserContext";

import "./style.scss";

const SellerTab = () => {
  const [activeTab, setActiveTab] = useState(SELLER_TABS[0].id);
  const [status, setStatus] = useState(PRODUCT_STATUS.ACTIVE);
  const [page, setPage] = useState(0);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [hasMore, setHasMore] = useState(true);
  
  const { userId } = useUser();

  const onTabClick = (tabId) => {
    const newStatus = tabId === SELLER_TABS[0].id ? PRODUCT_STATUS.ACTIVE : PRODUCT_STATUS.SOLD;
    
    setStatus(newStatus);
    setActiveTab(tabId);
    setPage(0);
  }  

  const fetchProducts = () => {
    if (!userId) return;

    setLoading(true);

    findProductsByUserIdAndStatus(userId, status, page, BIDS_DEFAULT_PAGE_NUMBER)
      .then((products) => {
        setProducts((prevItems) =>
          page === 0 ? [...products.content] : [...prevItems, ...products.content]
        );
        setHasMore(products.last !== true);
      })
      .catch((error) => {
        setError(error.message);
      })
      .finally(() => {
        setLoading(false);
      });
  }

  useEffect(() => {
    fetchProducts();
  }, [userId, page, status]);

  const handlePageChange = () => {
    setPage((prevPage) => prevPage + 1);
  }

  if (error) return <ErrorComponent message={error} />;

  return (
    <div className="seller-tab-container">
      <div className="seller-tabs">
        { SELLER_TABS.map((tab) => (
          <span 
            key={ tab.id } 
            className={ `${ activeTab === tab.id ? "active" : "" } tab body-semibold` }
            onClick={ () => onTabClick(tab.id) }
          >
            { tab.label }
          </span>
        )) }
      </div>
      <ProductBidsTable 
        items={ products }
        buttonLabel={ BUTTON_LABELS.VIEW }
        tabId={ MY_ACCOUNT_TABS_MAP.SELLER } 
      />
      { hasMore && (
        <div className="load-more-btn">
          <Button 
            label={ BUTTON_LABELS.LOAD_MORE } 
            onButtonClick={ handlePageChange } 
            variant={ BUTTON_VARIANTS.FILLED }
            className="load-more-button"
          />
        </div>
      ) }
    </div>
  );
}

export default SellerTab;
