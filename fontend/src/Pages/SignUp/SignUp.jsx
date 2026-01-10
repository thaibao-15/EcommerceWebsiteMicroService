import { useState } from "react";
import styles from './SignIn.module.scss';
import classNames from 'classnames/bind';
import React from 'react';
import { Link } from 'react-router-dom';

const cx = classNames.bind(styles);

function SignUp() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!email || !password || !confirmPassword) {
      alert('Vui lòng nhập đầy đủ thông tin');
      return;
    }
    if (password !== confirmPassword) {
      alert('Mật khẩu xác nhận không khớp');
      return;
    }
    try {
      const response = await fetch(`http://localhost:8080/identity/users`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          username: email,
          password: password
        })
      });

      if (!response.ok) {
        const errorText = await response.text().catch(() => 'Đăng ký thất bại');
        alert(errorText || 'Đăng ký thất bại');
        return;
      }

      // Optional: parse response if backend returns data
      // const data = await response.json();
      alert('Đăng ký thành công');
      setEmail('');
      setPassword('');
      setConfirmPassword('');
    } catch (err) {
      alert('Không thể kết nối tới máy chủ. Vui lòng thử lại sau.');
    }
  };

  return (
    <div className={cx('signup-container')}>

      <form className={cx('signup-form')} onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Mật khẩu"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input
          type="password"
          placeholder="Xác nhận mật khẩu"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
        />
        <button type="submit" className={cx('signup-button')}>Đăng ký</button>

        <button type="button" className={cx('google-button')} onClick={() => alert('Login with Google')}>
          <span className={cx('google-icon')}>G</span>
          Đăng nhập bằng Google
        </button>

        <p className={cx('login-link')}>
          Đã có tài khoản? <Link to="/login">Đăng nhập</Link>
        </p>
      </form>
    </div>
  );
}

export default SignUp;