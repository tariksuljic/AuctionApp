import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { 
  Button, 
  Checkbox, 
  ProductGrid,
  ErrorComponent,
  LoadingComponent 
} from "src/components";

import {
  getProducts,
  getCategoriesWithSubcategories
} from "src/services";
import { collapse, expand } from "src/assets/icons";
import { SHOP_DEFAULT_PAGE_NUMBER } from "src/constants";

import "./style.scss";

const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

const Shop = () => {
  const [activeCategory, setActiveCategory] = useState();
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(0);
  const [categories, setCategories] = useState([]);
  const [hasMore, setHasMore] = useState(true);
  const [productsError, setProductsError] = useState(null);
  const [productsLoading, setProductsLoading] = useState(true);
  const [categoriesError, setCategoriesError] = useState(null);
  const [categoriesLoading, setCategoriesLoading] = useState(true);
  const [checked, setChecked] = useState({});

  const navigate = useNavigate();
  const query = useQuery();

  const categoryId = query.get("category");
  const searchProduct = query.get("search_product");

  const fetchProducts = () => {
    setProductsLoading(true);
    
    getProducts(page, SHOP_DEFAULT_PAGE_NUMBER, categoryId, searchProduct)
      .then((products) => {
        setItems((prevItems) =>
          page === 0 ? [...products.content] : [...prevItems, ...products.content]
        );
        setHasMore(!products.last);
      })
      .catch((error) => {
        setProductsError(error.message);
      }).finally(() => {
        setProductsLoading(false);
      });
  };

  const fetchCategories = () => {
    setCategoriesLoading(true);
    
    getCategoriesWithSubcategories()
      .then((categories) => {
        setCategories(categories);
        const activeCat = categories.find(cat => cat.id === categoryId);

        !!activeCat && setActiveCategory(activeCat.name)
      })
      .catch((err) => {
        setCategoriesError(err.message);
      }).finally(() => {
        setCategoriesLoading(false);
      });
  };

  // fetch products on page load
  useEffect(() => {
    fetchProducts();
  }, [page, categoryId, searchProduct]);

  useEffect(() => {
    fetchCategories();
  }, [categoryId]);

  const fetchNextPage = () => {
    setPage((prevPage) => prevPage + 1);
  };

  const handleCheckboxChange = (id, checked) => {
    setChecked(prev => ({ ...prev, [id]: checked }));
  };

  const handleCategoryChange = (categoryId) => {
    let url = "/shop";
    const queryParams = new URLSearchParams();

    if (activeCategory === categories.find(cat => cat.id === categoryId).name) {
      queryParams.delete("category");
      setActiveCategory(null);
    } else {
      queryParams.set("category", categoryId);
      setActiveCategory(categories.find(cat => cat.id === categoryId).name);
    }

    if (searchProduct) {
      queryParams.set("search_product", searchProduct);
    }

    url += queryParams.toString() ? `?${queryParams.toString()}` : "";

    navigate(url);
  };

  if(productsError || categoriesError) return <ErrorComponent error={ productsError || categoriesError } />;

  return (
    <div className="shop-container">
      { categoriesLoading ? ( <LoadingComponent /> ) : (
        <>
          <div className="categories">
            <span className="body-regular">PRODUCT CATEGORIES</span>
            <div className="category-list body-regular">
              { categories.map((category) => (
                <div key={ category.id } className="category-item body-regular">
                  <button
                    className={ `category-name ${activeCategory === category.name ? "active" : ""}` }
                    onClick={ () => handleCategoryChange(category.id) }
                  >
                    { category.name }
                    { activeCategory === category.name ? (
                      <img src={ collapse } alt="Collapse" />
                    ) : (
                      <img src={ expand } alt="Expand" />
                    ) }
                  </button>
                  { activeCategory === category.name && category.subCategories && (
                    <div className="subcategory-list">
                      { category.subCategories.map((subcategory) => (
                        <Checkbox
                          key={ subcategory.id }
                          label={ `${subcategory.name} (${subcategory.productCount})` }
                          onChange={ (checked) => handleCheckboxChange(subcategory.id, checked) }
                        />
                      )) }
                    </div>
                  ) }
                </div>
              )) }
            </div>
          </div>
        </>
      ) }
      <div className="product-list">
        <ProductGrid items={ items } />
        { hasMore && (
        <div className="explore-btn">
          <Button
            label="Explore More"
            variant="filled"
            onButtonClick={ fetchNextPage }
          />
        </div>
        ) }
      </div>
    </div>
  );
};

export default Shop;
