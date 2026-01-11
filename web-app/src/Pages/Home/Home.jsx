import styles from './Home.module.scss';
import classNames from 'classnames/bind';
import { useState, useEffect } from 'react';
import React from 'react';
import { Link } from 'react-router-dom';

const cx = classNames.bind(styles);

// Sample food products data



function FetchProducts() {
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(false)
  useEffect(
    () => {
      fetch('http://localhost:8080/identity/products')
        .then(response => response.json())
        .then(data => {
          if (data.code === 1000 && Array.isArray(data.result)) {
            setProducts(data.result);
          } else {
            setProducts([]); // phòng khi API lỗi
          }
          setLoading(true)
        })
        .catch(error => console.log('error', error))

    }
    , [])
  return (
    <div className={cx('home')}>
      <div className={cx('container')}>
        <h1 className={cx('title')}>Sản phẩm nổi bật</h1>

        <div className={cx('products-grid')}>
          {!loading && <p>Loading...</p>}

          {products.map((product) => (

            <Link to={`/products/${product.id}`} >

              <div key={product.id} className={cx('product-card')}>
                <div className={cx('product-image')}>
                  <img src={product.image} alt={product.name} />
                </div>
                <div className={cx('product-info')}>
                  <h3 className={cx('product-name')}>{product.name}</h3>
                  <p className={cx('product-description')}>{product.description}</p>
                  <div className={cx('product-price')}>{(product.price)}</div>
            
                </div>
              </div>
            </Link>
          ))}
        </div>
      </div>
    </div>

  );

}

function Home() {
  const handleAddToCart = (productId) => {
    console.log(`Added product ${productId} to cart`);
    // Add to cart logic here
  };

  const formatPrice = (price) => {
    return new Intl.NumberFormat('vi-VN').format(price) + ' VND';
  };

  return (
    <FetchProducts />);
}

export default Home;