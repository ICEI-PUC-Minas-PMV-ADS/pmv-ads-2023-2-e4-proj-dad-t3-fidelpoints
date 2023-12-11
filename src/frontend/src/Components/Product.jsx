import { useState, useEffect } from 'react';
import '../Style/produtos.css';
import { Link } from 'react-router-dom';
import Navbar from '../Layout/Navbar';
import axios from 'axios';
//import {fs as fs} from 'node:fs'
//import CadProduct from './CadProduct';

const Product = () => {
    
    const [products, setProducts] = useState([]);

      console.log("caiu")

      useEffect(() => {
        let mounted = true

        let teste = axios.get("http://localhost:8080/produtos", 
        {headers: {
          "Authorization" : 'Bearer ' + localStorage.getItem('token')
        }
       })
          .then((response) => {
            if (response.status == 200) {
                console.log(response.data);
                setProducts(response.data);
              }
  
          return ()=>mounted =false
          }).catch(error => {
                console.log(error);
          });;
  
          console.log("teste: " + teste);
      },[]);


     
    
      return (
        <div className="formulario">
          <Navbar />
          <form>
          <h2>Lista de produtos</h2>
          <div className="">
            <table className='suaClasseDeTabela'>
              <thead>
                <tr>
                  <th>Nome</th>
                  <th>Pontos</th>
                </tr>
              </thead>
              <tbody>
                {products ? (
                  products.map(product => (
                    <tr key={product.idProduto} className=''>
                      <td>{product.nomeProduto}</td>
                      <td>{product.pontos}</td>
                      <td><Link to={`/EditProduct/${product.id}`}>Editar</Link></td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4">Produtos não cadastrados</td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
          </form>
        </div>
      );
    }

export default Product;