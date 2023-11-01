import { Link } from "react-router-dom";
import "../Style/navbar.css"



function Navbar(){
    return(
        <div className="navbar">
            <ul className="ulMenu">
                <li className="listNav"><Link to= '/CadProduct'>Cadastros de Produtos</Link></li>
                <li className="listNav"><Link to= '/Product'>Produtos</Link></li>
                <li className="listNav"><Link to= '/loged'>Pedidos</Link></li>
                <li className="listNav"><Link to= '/CadastroCliente'>Cadastro de Cliente</Link></li>
                <li className="listNav"><Link to= '/Client'>Clientes</Link></li>
                <li className="listNav"><Link to= '/'>Logout</Link></li>
            
            </ul>
            <hr className="hrMenu"/>
        </div>

    )
}
export default Navbar;