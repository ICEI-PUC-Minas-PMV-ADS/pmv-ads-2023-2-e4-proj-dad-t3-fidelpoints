import { useState, useEffect } from 'react';
import './Formulario/Formulario.css';
import Navbar from '../Layout/Navbar';
//import { Link } from 'react-router-dom';
//import {fs as fs} from 'node:fs'
//import CadClient from './CadClient';
import axios from "axios";


const Loged = () => {
  
  const [produtos, setProdutos] = useState([
    { nomeCliente: 'João', dataRetirada: '2023-01-15', produto: 'Notebook', status: 'Em andamento' },
    { nomeCliente: 'Maria', dataRetirada: '2023-02-01', produto: 'Smartphone', status: 'Entregue' },
    { nomeCliente: 'Carlos', dataRetirada: '2023-03-10', produto: 'Tablet', status: 'Pendente' },
  ]);

  const handleStatusChange = (index, novoStatus) => {
    const novosProdutos = [...produtos];
    novosProdutos[index].status = novoStatus;
    setProdutos(novosProdutos);
  };

  console.log('INICIO TELA')


       useEffect(() => {
        let teste = axios.get("http://localhost:8080/pedidos", 
        {headers: {
          "Authorization" : 'Bearer ' + localStorage.getItem('token')
        }
       })
          .then((response) => {
            console.log(response.data);

            setProdutos(response.data);

            return response.data;
          }).catch(error => {
                console.log(error);
          });;

          console.log("teste: " + teste);
    
      },[]);



  return (
    <div className="formulario">
      <Navbar/>
      <form>
        <h2>Lista de Pedidos</h2>
        <table className='suaClasseDeTabela'>
          <thead>
            <tr>
              <th>Nome Cliente</th>
              <th>Data Venda</th>
              <th>Produto</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {produtos.map((produto, index) => (
              <tr key={index}>
                <td>{produto.nomeCliente}</td>
                <td>{produto.dataCompra}</td>
                <td>{produto.nomeProduto}</td>
                <td>
                  <select
                    value={produto.status}
                    onChange={(e) => handleStatusChange(index, e.target.value)}
                  >
                    <option value="AGUARDANDO">Aguardando</option>
                    <option value="CONFIRMADO">Confirmado</option>
                    <option value="RESGATADO">Cancelado</option>
                    <option value="CANCELADO">Cancelado</option>
                  </select>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </form>
    </div>
  );
};


export default Loged;
