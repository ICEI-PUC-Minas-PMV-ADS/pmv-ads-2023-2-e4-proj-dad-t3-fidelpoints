import { Link } from "react-router-dom";
import "../Style/navbar.css"
import Banner from "../Components/Banner";


function Navbar(){
    return(
        <div className="navbar">
            <Banner/>
            <ul className="ulMenu">
                <li className="listNav"><Link to= '/'>Login</Link></li>
                <li className="listNav"><Link to= '/CadProduct'>cadastros de produtos</Link></li>
                <li className="listNav"><Link to= '/Product'>Produtos</Link></li>
                <li className="listNav"><Link to= '/EditProduct'>Edite Produto</Link></li>
            </ul>
            <hr className="hrMenu"/>
        </div>

    )
}
export default Navbar;