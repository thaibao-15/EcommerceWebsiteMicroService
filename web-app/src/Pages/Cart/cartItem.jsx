import styles from './cartItem.module.scss';
import classNames from 'classnames/bind';
import React from 'react';

const cx = classNames.bind(styles);

function CartItem({ item, onUpdateQuantity }) {

  const totalPrice = item.product.price * item.quantity;

  return (
    <div className={cx('cart-item')}>
      <div className={cx('item-checkbox')}></div>

      <div className={cx('item-image')}>
        <img
          src={item.product.image || '/no-image.png'}
          alt={item.product.name}
          onError={(e) => {
            const imgEl = e.currentTarget;
            if (imgEl.src.includes('/no-image.png')) return;
            imgEl.onerror = null;
            imgEl.src = '/no-image.png';
          }}
        />
      </div>

      <div className={cx('item-info')}>
        <h3 className={cx('item-name')}>{item.product.name}</h3>
        <p className={cx('item-description')}>{item.product.description}</p>
        <div className={cx('item-meta')}>

          <span className={cx('item-stock')}>Tồn kho: {item.product.stock}</span>
        </div>
      </div>

      <div className={cx('item-price')}>
        <div className={cx('price')}>
          {item.product.price.toLocaleString('vi-VN')} VND
        </div>
        <div className={cx('total')}>
          Tổng: {totalPrice.toLocaleString('vi-VN')} VND
        </div>
      </div>

      <div className={cx('item-quantity')}>
        <div className={cx('quantity-controls')}>
          <button
            className={cx('quantity-btn', 'decrease')}
            onClick={() => onUpdateQuantity(item.id, Math.max(1, item.quantity - 1))}
            disabled={item.quantity <= 1}
          >
            -
          </button>
          <input
            type="number"
            value={item.quantity}
            onChange={(e) => {
              const val = parseInt(e.target.value) || 1;
              const bounded = Math.min(Math.max(1, val), item.product.stock);
              onUpdateQuantity(item.id, bounded);
            }}
            min="1"
            max={item.product.stock}
            className={cx('quantity-input')}
          />
          <button
            className={cx('quantity-btn', 'increase')}
            onClick={() => onUpdateQuantity(item.id, Math.min(item.product.stock, item.quantity + 1))}
            disabled={item.quantity >= item.product.stock}
          >
            +
          </button>
        </div>
      </div>

      <div className={cx('item-actions')}></div>
    </div>
  );
}

export default CartItem;
