import classNames from "classnames/bind";
import styles from './SideBar.module.scss';
import { Link } from "react-router-dom";


const cx = classNames.bind(styles);

function SideBar(){
    return (
        <aside className={cx('wrapper')}>
            <h1>TikTok SideBar</h1>
            <nav>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/following">Following</Link></li>
                    <li><Link to="/profile">Profile</Link></li>
                </ul>
            </nav>
        </aside>
    );
}

export default SideBar;