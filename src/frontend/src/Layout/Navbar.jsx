import { Link } from "react-router-dom";
import React, { useState } from 'react';
import "../Style/navbar.css"



function Navbar() {
    const [menuExpandido, setMenuExpandido] = useState(false);
  
    const toggleMenu = () => {
      setMenuExpandido(!menuExpandido);
    };
  
    return (
      <div className={`navbar ${menuExpandido ? 'expandido' : ''}`}>
        <button className="btnToggle" onClick={toggleMenu}>
          ☰
        </button>
        <ul className={`ulMenu ${menuExpandido ? 'expandido' : ''}`}>
          <li className="listNav"><Link to='/CadProduct'>Cadastro de Produtos</Link></li>
          <li className="listNav"><Link to='/Product'>Produtos</Link></li>
          <li className="listNav"><Link to='/loged'>Pedidos</Link></li>
          <li className="listNav"><Link to='/CadastroCliente'>Cadastro de Cliente</Link></li>
          <li className="listNav"><Link to='/Client'>Clientes</Link></li>
          <li className="listNav"><Link to='/'>Logout</Link></li>
        </ul>
        <hr className="hrMenu" />
      </div>
    );
  }
  
  export default Navbar;