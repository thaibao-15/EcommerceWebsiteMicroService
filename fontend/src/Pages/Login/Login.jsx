import styles from './Login.module.scss';
import classNames from 'classnames/bind';
import { useState } from 'react';
import React from 'react';
import { Link } from 'react-router-dom';
import { setToken } from 'src/Service/Localstorage';
const cx = classNames.bind(styles);



function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    

    const handleSubmit = (e) => {
        e.preventDefault();

        fetch("http://localhost:8080/identity/auth/token", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        })
            .then(async (res) => {
                const data = await res.json();
                if (!res.ok) {
                    throw new Error(data.message || "Login failed");
                }
                return data;
            })
            .then((data) => {
                console.log("Server response:", data);

                // ✅ Kiểm tra tồn tại trước khi truy cập
                const token = data?.result?.token || data?.token;
                if (!token) throw new Error("Token không tồn tại trong phản hồi!");

                setToken(token);
                console.log("Login success, token:", token);
                console.log(localStorage.getItem("token"));

                window.location.href = "/";
            })
            .catch((err) => {
                console.error("Error:", err.message);
                alert("Đăng nhập thất bại: " + err.message);
            });
    };

    const handleGoogleLogin = () => {
        // Redirect to backend Google OAuth2 endpoint (adjust if different)
        window.location.href = "http://localhost:8080/identity/oauth2/authorization/google";
    };





    return (
        <div className={cx('login-container')}>
        
            <form className={cx('login-form')} onSubmit={handleSubmit} >

                <input type='text' placeholder='Tên đăng nhập'
                    value={username}
                    onChange={(e) => setUsername(e.target.value)} />
                <input type='password' placeholder='Mật khẩu'
                    value={password}
                    onChange={e => setPassword(e.target.value)} />

                <button type='submit' className={cx('login-button')} >Đăng nhập</button>
                <button type='button' className={cx('google-button')} onClick={handleGoogleLogin}>
                    <span className={cx('google-icon')}>G</span>
                    Đăng nhập bằng Google
                </button>
                <p className={cx('signup-link')}>Chưa có tài khoản? <Link to="/signup">Đăng ký</Link></p>
            </form>

        </div>
    )
}

export default Login;


