import { useState } from 'react';
import '../Style/client.css';
import'../Components/Formulario'
import { Link } from 'react-router-dom';
import Navbar from '../Layout/Navbar';
//import {fs as fs} from 'node:fs'
//import CadClient from './CadClient';

function Client({postProduto}){
    const [client, setClient] = useState([
        {'name':"Edglei", 'description':"Só testando pra ver",'image':Blob, 'points':"200"},
        {'name':"Edivania", 'description':"Só testando pra ver",'image':Blob, 'points':"200"},
        {'name':"Ana", 'description':"Só testando pra ver",'image':Blob, 'points':"200"}
        ]);

        let teste = axios.get("http://localhost:8080/pontos", 
        {headers: {
          "Authorization" : 'Bearer ' + localStorage.getItem('token')
        }
       })
          .then((response) => {
            console.log(response.data);
  
           // setProdutos(response.data);
          setProducts(response.data)
  
            return response.data;
          }).catch(error => {
                console.log(error);
          });;
  
          console.log("teste: " + teste);
        
    
    return(
        <div className="layoutClient">
            <Navbar/>
            <h2>Lista de Clientes</h2>
            <div className="client">
                <ul className='listClient'>
                    <li className='list'>
                                <li className='cabecalho'> Nome</li> 
                                <li className='cabecalho'> Descrição:</li>
                                <li className='cabecalho'> Pontos:</li>
                    </li>
                    <hr />
                    {client ?
                        client.map(p =>
                            <li className='list'>
                                <li> {p.name} </li> 
                                <li> {p.description} </li>
                                <li> {p.points}</li>
                            </li>)
                        : <h2>Cliente não cadastrado</h2>
                    }
                </ul>
            </div>
            
        </div>
    )
}

export default Client;