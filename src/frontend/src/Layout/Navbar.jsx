import { Link } from "react-router-dom";
import "../Style/navbar.css"
import Banner from "../Components/Banner";


function Navbar(){
    return(
        <div className="navbar">
            <Banner/>
            <ul className="ulMenu">
                <li className="listNav"><Link to= '/'>Login</Link></li>
                <li className="listNav"><Link to= '/CadProduct'>Cadastros de Produtos</Link></li>
                <li className="listNav"><Link to= '/Product'>Produtos</Link></li>
                <li className="listNav"><Link to= '/EditProduct'>Editar Produtos</Link></li>
                <li className="listNav"><Link to= '/CadastroCliente'>Cadastro de Cliente</Link></li>
                <li className="listNav"><Link to= '/Client'>Clientes</Link></li>
                <li className="listNav"><Link to= '/EditClient'>Editar Clientes</Link></li>
            </ul>
            <hr className="hrMenu"/>
        </div>

    )
}
export default Navbar;