import "./style.scss";

const Tabs = ({ tabs, activeTab, onTabClick }) => {
  return (
    <div className="tabs">
      { tabs.map((tab) => (
        <span
          key={ tab.id }
          onClick={ () => onTabClick(tab.id) }
          className={ `tab ${activeTab === tab.id ? "active" : "inactive"}` }
        >
          { tab.label }
        </span>
      )) }
    </div>
  );
};

export default Tabs;
