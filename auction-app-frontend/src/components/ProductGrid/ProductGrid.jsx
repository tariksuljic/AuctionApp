import { ProductCard } from 'src/components';

import './style.scss';

const ProductGrid = ({ items }) => {
  return (
    <div className="product-grid">
      { items.map((item) => (
        <ProductCard key={ item.id } { ...item } />
      )) }
    </div>
  );
};

export default ProductGrid;
