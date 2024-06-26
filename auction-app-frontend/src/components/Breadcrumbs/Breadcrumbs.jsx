import { Link } from "react-router-dom";

import { useBreadcrumb } from "src/store/BreadcrumbContext";

import { next } from "src/assets/icons";
import { SEARCH_RESULTS } from "src/constants";

import "./style.scss";

const Breadcrumbs = () => {
  const { title, breadcrumbs, hideBreadcrumbs } = useBreadcrumb();

  if (hideBreadcrumbs) return null;

  const isSearchPage = breadcrumbs.some(crumb => crumb.label && crumb.label.startsWith(SEARCH_RESULTS));

  return (
    <div className="breadcrumbs">
      { !isSearchPage && (
        <div className="breadcrumbs-left body-bold">
            <div className="breadcrumbs-left body-bold">{ title }</div>
        </div>
      ) }
      <div className="breadcrumbs-right">
        { breadcrumbs.map((crumb, index) => (
          <span key={ index } className="body-regular">
            { index > 0 && <img src={ next } alt="Next Page" />}
            { index < breadcrumbs.length - 1 ? (
              <Link to={ crumb.path }>{ crumb.label }</Link>
            ) : (
              <span className="current body-bold">{ crumb.label }</span>
            ) }
          </span>
        )) }
      </div>
    </div>
  );
};

export default Breadcrumbs;
