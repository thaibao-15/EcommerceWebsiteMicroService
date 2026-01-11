import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { Fragment } from 'react';
import '~/App.css';
import DefaultLayout from '~/Layouts/DefaultLayout';

import { publicRoutes } from '~/router';
import { useState, useEffect } from 'react';
import { introspect } from './Service/authentication';



function App() {

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      // Kiểm tra token hợp lệ nếu cần
      introspect(token).then(data => {
        if (data.result.valid) {
          console.log('Token is valid:', data);
        } else {
          console.log('Token is invalid or expired');
          localStorage.removeItem('token'); // Xoá token không hợp lệ
        }
      }).catch(err => {
        console.error('Error during token introspection:', err);
        localStorage.removeItem('token'); // Xoá token nếu có lỗi
      });

    } else {
      console.log('No token found, user is not logged in.');
    }
  });
  return (
    <Router>
      <div>
        <Routes>
          {publicRoutes.map((route, index) => {
            const Page = route.component

            let Layout = DefaultLayout
            if (route.layout) {
              Layout = route.layout
            } else if (route.layout === null) {
              Layout = Fragment
            }

            return <Route
              key={index}
              path={route.path}
              element={<Layout><Page /></Layout>} />
          })}

        </Routes>
      </div>
    </Router>
  );
}

export default App;
