import { useState, useEffect } from "react";
import { useLocation, Link } from "react-router-dom";

import { Button, ProfileTab, SellerTab, BidsTab, SettingsTab } from "src/components";

import { MY_ACCOUNT_TABS, BUTTON_LABELS, BUTTON_VARIANTS, MY_ACCOUNT_TABS_MAP, ROUTE_PATHS } from "src/constants";

import "./style.scss";

const MyAccount = () => {
  const location = useLocation();

  const initialTab = location.hash ? location.hash.replace('#', '') : MY_ACCOUNT_TABS[0].id;

  const [activeTab, setActiveTab] = useState(initialTab);

  const appendHash = (newActiveTab) => {
    const hash = location.hash.replace('#', '');

    if (!hash || hash !== newActiveTab) {
      window.location.hash = newActiveTab;
    }
  };

  useEffect(() => {
    appendHash(activeTab); 
  }, []);

  const changeActiveTab = (tabId) => () => {
    setActiveTab(tabId);
    appendHash(tabId);
  };

  const renderActiveTabContent = () => {
    switch (activeTab) {
      case MY_ACCOUNT_TABS_MAP.PROFILE:
        return <ProfileTab />;
      case MY_ACCOUNT_TABS_MAP.SELLER: 
        return <SellerTab />;
      case MY_ACCOUNT_TABS_MAP.BIDS:
        return <BidsTab />;
      case MY_ACCOUNT_TABS_MAP.SETTINGS:
        return <SettingsTab />;
      default:
        return null;
    }
  };

  return (
    <div className="my-account-options-container">
      <div className="my-account-tabs">
        <div className="tab-options">
          { MY_ACCOUNT_TABS.map((tab) => (
            <span
              key={ tab.id }
              onClick={ changeActiveTab(tab.id) }
              className={ `${ activeTab === tab.id ? "active" : "inactive" } tab body-semibold` }
            >
              <img 
                  src={ activeTab === tab.id ? tab.activeIcon : tab.icon } 
                  alt={ `${ tab.label } icon` } 
                  className="tab-icon" 
              />
              { tab.label }
            </span> 
          )) }
        </div>
        <div className="add-item-button">
          <Link to={ ROUTE_PATHS.ADD_ITEM }>
            <Button label={ BUTTON_LABELS.ADD_ITEM } variant= { BUTTON_VARIANTS.FILLED } />
          </Link>
        </div>
      </div>
      <div>
        { renderActiveTabContent() }
      </div>
    </div>
  )
}

export default MyAccount;
