import styles from './DetailProduct.module.scss';
import classNames from 'classnames/bind';
import { useState, useEffect } from 'react';
import React from 'react';
import { useParams } from 'react-router-dom';
import { getToken } from '~/Service/Localstorage';

const cx = classNames.bind(styles);

const token = getToken();

function DetailProduct() {
  const { id } = useParams(); // lấy id từ URL
  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);

  const handleAddToCart = ({ idProduct, quantity }) => {
    fetch(`http://localhost:8080/identity/cartItems`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({
        productId: idProduct,
        quantity: quantity
      }),
    })
      .then(res => res.json())
      .then(data => {
        if (data.code === 1000) {
          alert('Thêm vào giỏ hàng thành công!');
        } else {
          alert('Thêm vào giỏ hàng thất bại!');
        }
      })
      .catch(err => {
        console.error(err);
        alert('Đã xảy ra lỗi khi thêm vào giỏ hàng!');
      });

  }

  useEffect(() => {
    fetch(`http://localhost:8080/identity/products/${id}`) // API chi tiết
      .then(res => res.json())
      .then(data => {
        // Nếu API của bạn trả { code, result }
        if (data.code === 1000) {
          setProduct(data.result);
        }
      })
      .catch(err => console.error(err));
  }, [id]);

  if (!product) return <p>Đang tải sản phẩm...</p>;

  return (
    <div className={cx('container')}>
      <div className={cx('card')}>
        <div className={cx('layout')}>
          <div className={cx('imageWrap')}>
            <img src={product.image || "/no-image.png"} alt={product.name} />
          </div>
          <div className={cx('info')}>
            <h1 className={cx('name')}>{product.name}</h1>
            <div className={cx('price')}>{product.price.toLocaleString("vi-VN")} VND</div>
            <div className={cx('stock')}>Tồn kho: {product.stock}</div>
            <div className={cx('divider')} />
            <div className={cx('description')}>{product.description}</div>
            <div className={cx('divider')} />
            <div className={cx('metaRow')}>
              <div className={cx('label')}>Số lượng</div>
              <div className={cx('value')}>
                <button
                  className={cx('qtyBtn')}
                  onClick={() => setQuantity(q => Math.max(1, q - 1))}
                  disabled={quantity <= 1}
                >-</button>
                <input
                  className={cx('qtyInput')}
                  type="number"
                  min="1"
                  max={product?.stock || 999}
                  value={quantity}
                  onChange={(e) => {
                    const val = parseInt(e.target.value) || 1;
                    setQuantity(Math.min(Math.max(1, val), product?.stock || 999));
                  }}
                />
                <button
                  className={cx('qtyBtn')}
                  onClick={() => setQuantity(q => Math.min((product?.stock || 999), q + 1))}
                  disabled={quantity >= (product?.stock || 999)}
                >+</button>
              </div>
            </div>
            <div className={cx('divider')} />
          
            <div className={cx('actions')}>
              <button
                className={cx('button', 'primary')}
                onClick={() => handleAddToCart({ idProduct: product.id || id, quantity })}
              >
                Thêm vào giỏ
              </button>
              <button className={cx('button', 'secondary')}>Mua ngay</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DetailProduct;


