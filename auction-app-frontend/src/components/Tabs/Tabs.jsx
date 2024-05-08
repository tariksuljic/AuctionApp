import "./style.scss";

const Tabs = ({ tabs, activeTab, onTabClick }) => {
  const handleTabClick = (tabId) => {
    onTabClick(tabId);
  };

  return (
    <div className="tabs">
      { tabs.map((tab) => (
        <span
          key={ tab.id }
          onClick={ () => handleTabClick(tab.id) }
          className={ `${ activeTab === tab.id ? "active" : "inactive" } tab` }
        >
          { tab.label }
        </span>
      )) }
    </div>
  );
};

export default Tabs;
