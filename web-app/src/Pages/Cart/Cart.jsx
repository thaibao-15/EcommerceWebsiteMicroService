import styles from './Cart.module.scss';
import classNames from 'classnames/bind';
import { useState, useEffect } from 'react';
import React from 'react';
import CartItem from './cartItem';
import { getToken } from '~/Service/Localstorage';

const cx = classNames.bind(styles);

function Cart() {
  // Dữ liệu cứng cho giỏ hàng
  const [cartItems, setCartItems] = useState([]);

  const [loading, setLoading] = useState(false);

  useEffect(() => {
    let isMounted = true;
    const fetchCartItems = async () => {
      try {
        setLoading(true);
        const res = await fetch(`http://localhost:8080/identity/cartItems`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${getToken()}`
          }
        });
        const data = await res.json();
        if (!res.ok) throw new Error(data.message || "Fetch failed");
        if (isMounted) {
          const items = data && data.code === 1000 && Array.isArray(data.result) ? data.result : [];
          const mapped = items.map(x => ({
            id:x.id,
            idProduct: x.productId, // dùng productId làm id mục giỏ hàng
            quantity: x.quantity,
            product: {
              idProduct: x.productId,
              name: x.productName,
              description: x.description,
              price: x.price,
              stock: x.stock,
              image: x.image,
              quantity: x.quantity
            }
          }));
          setCartItems(mapped);
        }
      } catch (error) {
        console.log("error", error.message);
      } finally {
        if (isMounted) setLoading(false);
      }
    };
    fetchCartItems();
    return () => { isMounted = false; };
  }, []);

  const totalPrice = cartItems.reduce((sum, item) => sum + (item.product.price * item.quantity), 0);

  const handleUpdateQuantity = async (cartItemId, newQuantity) => {
    if (newQuantity < 1) return;
    // Optimistic UI update
    setCartItems(prev => prev.map(item => item.id === cartItemId ? { ...item, quantity: newQuantity } : item));
    try {
      await fetch(`http://localhost:8080/identity/cartItems`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${getToken()}`
        },
        body: JSON.stringify({
          id: cartItemId,
          quantity: newQuantity
        })
      });
    } catch (e) {
      console.log('update quantity error', e?.message || e);
    }
  };

  if (loading) {
    return <div className={cx('loading')}>Đang tải giỏ hàng...</div>;
  }

  return (
    <div className={cx('container')}>
      <div className={cx('header')}>
        <h1>Giỏ hàng của bạn</h1>
        <span className={cx('item-count')}>({cartItems.length} sản phẩm)</span>
      </div>

      {cartItems.length === 0 ? (
        <div className={cx('empty-cart')}>
          <div className={cx('empty-icon')}>🛒</div>
          <h3>Giỏ hàng trống</h3>
          <p>Hãy thêm sản phẩm vào giỏ hàng để bắt đầu mua sắm!</p>
        </div>
      ) : (
        <>
          <div className={cx('cart-content')}>
            <div className={cx('items-section')}>
              <div className={cx('select-all')}>
                <label className={cx('checkbox-wrapper')}>
                  <span className={cx('checkmark')}></span>
                  Chọn tất cả ({cartItems.length})
                </label>
              </div>

              <div className={cx('items-list')}>
                {cartItems.map(item => (
                  <CartItem
                    key={item.id}
                    item={item}
                    onUpdateQuantity={handleUpdateQuantity}
                  />
                ))}
              </div>
            </div>

            <div className={cx('summary-section')}>
              <div className={cx('summary-card')}>
                <div className={cx('summary-header')}>
                  <h3>Tóm tắt đơn hàng</h3>
                </div>

                <div className={cx('summary-content')}>
                  <div className={cx('summary-row')}>
                    <span>Tạm tính ({cartItems.length} sản phẩm):</span>
                    <span>{totalPrice.toLocaleString('vi-VN')} VND</span>
                  </div>

                  <div className={cx('summary-row')}>
                    <span>Phí vận chuyển:</span>
                    <span className={cx('free-shipping')}>Miễn phí</span>
                  </div>

                  <div className={cx('summary-row', 'total')}>
                    <span>Tổng cộng:</span>
                    <span className={cx('total-price')}>{totalPrice.toLocaleString('vi-VN')} VND</span>
                  </div>
                </div>

              </div>
            </div>
          </div>
        </>
      )}
    </div>
  );
}

export default Cart;
