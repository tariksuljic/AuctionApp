import { useState } from "react";

import { ProductBidsTable } from "src/components";

import { SELLER_TABS, MY_ACCOUNT_TABS_MAP } from "src/constants";

import "./style.scss";

const SellerTab = () => {
  const [activeTab, setActiveTab] = useState(SELLER_TABS[0].id);

  const onTabClick = (tabId) => {
    setActiveTab(tabId);
  }  

  return (
    <div className="seller-tab-container">
      <div className="seller-tabs">
        { SELLER_TABS.map((tab) => (
          <span 
            key={ tab.id } 
            className={ `${ activeTab === tab.id ? 'active' : '' } tab body-semibold` }
            onClick={ () => onTabClick(tab.id) }
          >
            { tab.label }
          </span>
        )) }
      </div>
      <ProductBidsTable tabId={ MY_ACCOUNT_TABS_MAP.SELLER } />
    </div>
  )
}

export default SellerTab;
