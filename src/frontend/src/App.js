import { BrowserRouter as Router, Routes , Route } from "react-router-dom";
import './Style/App.css';
//import { useState } from 'react';
import CadProduct from './Components/CadProduct';
import Navbar from "./Layout/Navbar";
import Login from "./Components/Login";
import Product from "./Components/Product";
import EditProduct from "./Components/editProduct";


function App() {
//  const[edg, setEdg] = useState("Edglei");

  return (
    <Router>
      <Navbar/>
      <Routes>
        <Route exact path="/" element={<Login/>}/>
            
        <Route path="/CadProduct" element={<CadProduct/>}/>

        <Route path="/Product" element={<Product/>}/>
        <Route path="/EditProduct" element={<EditProduct/>}/>
        
            
      </Routes>
    </Router>    
  );
}

export default App;
