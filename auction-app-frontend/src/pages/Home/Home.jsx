import { useState, useEffect } from "react";

import {
  ProductGrid,
  ErrorComponent,
  LoadingComponent,
  Button,
} from "src/components";

import {
  getProductsPaginated,
  getProductRandom,
  getTopLevelCategories,
} from "src/services";

import { go } from "src/assets/icons";

import { NEW_ARRIVALS, LAST_CHANCE } from "src/constants";

import "./style.scss";

const Home = () => {
  const [activeTab, setActiveTab] = useState(NEW_ARRIVALS);
  const [page, setPage] = useState(0);
  const [items, setItems] = useState([]);
  const [hasMore, setHasMore] = useState(true);
  const [product, setProduct] = useState(null);
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // method to fetch initial data
  const fetchInitialData = () => {
    setLoading(true);

    Promise.all([getProductRandom(), getTopLevelCategories()])
      .then(([randomProduct, topLevelCategories]) => {
        setProduct(randomProduct);
        setCategories(topLevelCategories);
      })
      .catch((err) => {
        setError("Failed to fetch initial data: " + err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  // method to get products based on activeTab and page
  const fetchProducts = () => {
    getProductsPaginated(activeTab, page, 8)
      .then((res) => {
        setItems((prevItems) =>
          page === 0 ? [...res.content] : [...prevItems, ...res.content]
        );
        setHasMore(res.content.length > 0);
      })
      .catch((err) => {
        setError(err.message);
      });
  };

  // to fetch initial data on component mount
  useEffect(() => {
    fetchInitialData();
  }, []);

  // to fetch products when activeTab or page changes
  useEffect(() => {
    fetchProducts();
  }, [activeTab, page]);

  const setActiveTabHandler = (tabName) => {
    setActiveTab(tabName);
    setPage(0);
    setItems([]);
    setHasMore(true);
  };

  const fetchNextPage = () => {
    setPage((prevPage) => prevPage + 1);
  };

  if (loading) return <LoadingComponent />;
  if (error) return <ErrorComponent message={error} />;

  return (
    <>
      <div className="home-container">
        <div className="home-upper">
          <div className="categories body-regular">
            <div className="categories-heading">Categories</div>
            <ul>
              {categories.map((category) => (
                <li key={category.id}>{category.name}</li>
              ))}
              <li>All Categories</li>
            </ul>
          </div>
          <div className="highlighted-product">
            <div className="product-container">
              <div className="product-info body-semibold">
                <span className="product-name">{product.name}</span>
                <span className="product-price">
                  Start From ${product.startPrice}
                </span>
                <span className="body-regular">{product.description}</span>
                <Button label="Bid now" iconSrc={go} />
              </div>
            </div>
            <div className="product-image">
              <img src={product.imageUrl} alt={product.name} />
            </div>
          </div>
        </div>
        <div className="products">
          <div className="tabs">
            <span
              onClick={() => setActiveTabHandler(NEW_ARRIVALS)}
              id={NEW_ARRIVALS}
              className={`tab ${
                activeTab === NEW_ARRIVALS ? "active" : "inactive"
              }`}
            >
              New Arrivals
            </span>
            <span
              onClick={() => setActiveTabHandler(LAST_CHANCE)}
              id={LAST_CHANCE}
              className={`tab ${
                activeTab === LAST_CHANCE ? "active" : "inactive"
              }`}
            >
              Last Chance
            </span>
          </div>
          <ProductGrid
            key={activeTab}
            items={items}
            fetchMoreData={fetchNextPage}
            hasMore={hasMore}
            loading={loading}
            activeTab={activeTab}
          />
        </div>
      </div>
    </>
  );
};

export default Home;
