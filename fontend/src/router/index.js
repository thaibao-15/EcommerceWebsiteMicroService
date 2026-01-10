import Home from '~/Pages/Home';
import Following from '~/Pages/Following';
import Profile from '~/Pages/Profile';
import Upload from '~/Pages/Upload';
import Header from '~/Layouts/Components/Header';
import DetailProduct from '~/Pages/DetailProduct';
import Login from '~/Pages/Login';
import SignUp from '~/Pages/SignUp';
import Cart from '~/Pages/Cart';

//không cần login
const publicRoutes = [
    { path: '/', component: Home },
    { path: 'following', component: Following },
    { path: 'profile', component: Profile, layout: null },
    { path: 'upload', component: Upload, layout: Header },
    { path: 'products/:id', component: DetailProduct },
    { path: 'login', component: Login, layout: null },
    { path: 'signup', component: SignUp, layout: null },
    { path: 'cart', component: Cart },

    // layout null để không sử dụng layout mặc định,

]

//cần login
const privateRoutes = []

export { publicRoutes, privateRoutes }