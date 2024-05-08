import { EMPTY_TABS } from "src/constants";

import "./style.scss"

const EmptyTab = ({ tabId }) => {
    const tabData = EMPTY_TABS.find((tab) => tab.id === tabId);

    return (
        <div className="empty-tab-container">
            <img src={ tabData.icon } alt={ `${ tabId } icon` } className="no-items-icon" />
            <span className="body-regular">{ tabData.text }</span>
            <span className="body-bold">{ tabData.boldText }</span>
        </div>
    )
}

export default EmptyTab;
