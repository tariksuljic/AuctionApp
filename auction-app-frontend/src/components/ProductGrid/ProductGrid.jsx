import useInfiniteScroll from "react-infinite-scroll-hook";

import { ProductCard, LoadingComponent } from "src/components";

import "./style.scss";

const ProductGrid = ({ items, fetchMoreData, hasMore, loading }) => {
  const [sentryRef] = useInfiniteScroll({
    loading,
    hasNextPage: hasMore,
    onLoadMore: fetchMoreData,
    rootMargin: "0px 0px 400px 0px",
  });

  return (
    <div className="product-grid">
      {items.map((item) => (
        <ProductCard key={item.id} {...item} />
      ))}
      {(loading || hasMore) && (
        <div ref={sentryRef}>
          <LoadingComponent />
        </div>
      )}
    </div>
  );
};
export default ProductGrid;
