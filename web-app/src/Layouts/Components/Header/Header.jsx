import styles from './Header.module.scss';
import classNames from 'classnames/bind';
import { Link, useNavigate } from 'react-router-dom';
import { getToken, removeToken } from '~/Service/Localstorage';
import React, { useState } from 'react';

const cx = classNames.bind(styles);

function Header() {
  const navigate = useNavigate();
  const [openUserMenu, setOpenUserMenu] = useState(false);
    const handleCartClick = () => {
        console.log('Cart clicked');
        // Navigate to cart page or open cart modal
    };
  const handleLogout = () => {
    removeToken();
    setOpenUserMenu(false);
    navigate('/login');
  };

    return (
        <header className={cx('wrapper')}>
            {/* Logo */}
            <Link to="/" className={cx('logo')}>
                WebBanHang
            </Link>

            {/* Navigation */}
            <nav className={cx('nav')}>
                <ul className={cx('nav-list')}>
                    <li className={cx('nav-item')}>
                        <Link to="/">Trang chủ</Link>
                    </li>
                    <li className={cx('nav-item')}>
                        <Link to="/following">Theo dõi</Link>
                    </li>
                    <li className={cx('nav-item')}>
                        <Link to="/profile">Hồ sơ</Link>
                    </li>
                </ul>
            </nav>

            {/* User Actions */}
            <div className={cx('user-actions')}>
                {/* Search Box */}
                <div className={cx('search-box')}>
                    <span className={cx('search-icon')}>🔍</span>
                    <input type="text" placeholder="Tìm kiếm..." />
                </div>

                {/* User Menu */}
                <div className={cx('user-menu')}>
                    {/* Shopping Cart */}
                    <div className={cx('cart-icon')} onClick={handleCartClick}>
                        <Link to="/cart">🛒  .</Link>
                        <span className={cx('badge')}>2</span>
                    </div>

                    {/* Notification Icon */}
                    <div className={cx('notification-icon')}>
                        🔔
                        <span className={cx('badge')}>3</span>
                    </div>

                    {/* User Avatar / Login */}
                    {getToken() ? (
                      <div className={cx('avatar-wrapper')}>
                        <button
                          type="button"
                          className={cx('avatar')}
                          onClick={() => setOpenUserMenu(v => !v)}
                          aria-haspopup="menu"
                          aria-expanded={openUserMenu}
                        >
                          B
                        </button>
                        {openUserMenu && (
                          <div className={cx('user-dropdown')} role="menu">
                            <Link to="/profile" className={cx('dropdown-item')} onClick={() => setOpenUserMenu(false)}>Hồ sơ</Link>
                            <button className={cx('dropdown-item', 'danger')} onClick={handleLogout}>Đăng xuất</button>
                          </div>
                        )}
                      </div>
                    ) : (
                      <Link to="/login" className={cx('login-link')}>
                        Đăng nhập
                      </Link>
                    )}
                </div>
            </div>
        </header>
    );
}

export default Header;