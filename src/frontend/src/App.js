import { BrowserRouter as Router, Routes , Route } from "react-router-dom";
import './Style/App.css';
//import { useState } from 'react';
import CadProduct from './Components/CadProduct';
//import Navbar from "./Layout/Navbar";
import Login from "./Components/Login/Login";
import CadastroLojista from "./Components/CadastroLojista/CadastroLojista";
import Product from "./Components/Product";
import EditProduct from "./Components/editProduct";
import RecuperarSenha from "./Components/RecuperarSenha";
import RedefinirSenha from "./Components/RedefinirSenha";
import CadastroCliente from "./Components/CadastroCliente/CadastroCliente";
import ClientList from "./Components/ClienteList/ClientList";
import EditClient from "./Components/CadClients/EditClient";
import Loged from "./Components/loged";
import Banner from "./Components/Banner/Banner";


function App() {
  
  return (
  <Router>
    <Banner/>
    <Routes>
      <Route exact path="/" element={<Login/>}/>
      <Route path="/loged" element={<Loged/>}/>
      <Route path="/CadastroLogista" element={<CadastroLojista/>}/>
      <Route path="/RecuperarSenha" element={<RecuperarSenha/>}/>
      <Route path="/RedefinirSenha" element={<RedefinirSenha/>}/>
      <Route path="/CadastroCliente" element={<CadastroCliente/>}/>
      <Route path="/EditClient" element={<EditClient/>}/>
      <Route path="/CadProduct" element={<CadProduct/>}/>
      <Route path="/Product" element={<Product/>}/>
      <Route path="/Client" element={<ClientList/>}/>
      <Route path="/EditProduct" element={<EditProduct/>}/>
      
          
    </Routes>
  
  </Router>
  
    
  );
}

export default App;
