import { BrowserRouter as Router, Routes , Route } from "react-router-dom";
import './Style/App.css';
import { useState } from 'react';
import CadProduct from './Components/CadProduct';
import Navbar from "./Layout/Navbar";
import Login from "./Components/Login";
import CadastroLogista from "./Components/CadastroLogista";
import Product from "./Components/Product";
import EditProduct from "./Components/editProduct";
import RecuperarSenha from "./Components/RecuperarSenha";
import RedefinirSenha from "./Components/RedefinirSenha";
import CadastroCliente from "./Components/CadastroCliente";


function App() {
  
  return (

    <Router>
      <Navbar/>     
      <Routes>
        <Route exact path="/" element={<Login/>}/>

        <Route path="/CadastroLogista" element={<CadastroLogista/>}/>
        <Route path="/RecuperarSenha" element={<RecuperarSenha/>}/>
        <Route path="/RedefinirSenha" element={<RedefinirSenha/>}/>
        <Route path="/CadastroCliente" element={<CadastroCliente/>}/>
            
        <Route path="/CadProduct" element={<CadProduct/>}/>
        <Route path="/Product" element={<Product/>}/>
        <Route path="/EditProduct" element={<EditProduct/>}/>
        
            
      </Routes>
    </Router>    
  );
}

export default App;
