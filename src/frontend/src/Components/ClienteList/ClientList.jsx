import './ClientList.css'
import { Link } from 'react-router-dom';
import Navbar from '../../Layout/Navbar';
import '../Formulario/Formulario.css'
import axios from 'axios';

import { useState, useEffect } from 'react';

const ClientList = () => {
  const [menuOpen, setMenuOpen] = useState(false);

  const handleToggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  const [clients, setClients] = useState([]);


  useEffect(() => {
    let mounted = true

    let teste = axios.get("http://localhost:8080/clientes",
      {
        headers: {
          "Authorization": 'Bearer ' + localStorage.getItem('token')
        }
      })
      .then((response) => {
        console.log(response.data);

        setClients(response.data)

        return () => mounted = false
      }).catch(error => {
        console.log(error);
      });;

    console.log("teste: " + teste);
  }, []);

  return (
    <div className="formulario">
      <Navbar />
      <form>
        <h2 >Lista de Clientes</h2>
        <table className="suaClasseDeTabela">
          <thead>
            <tr>
              <th>Nome</th>
              <th>Data ultimo pedido</th>
              <th>Celular</th>
              <th>Pontos</th>
            </tr>
          </thead>
          <tbody>

            {clients.length > 0 ? (
              clients.map((client) => (
                <tr key={client.id}>
                  <td>{client.nome}</td>
                  <td>{client.dataUltimoPedido}</td>
                  <td>{client.celular}</td>
                  <td>{client.quantidadePontos}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4" className="client-list-cell client-list-no-clients">
                  Clientes não cadastrados
                </td>
              </tr>
            )}
          </tbody>
        </table></form>
    </div>
  );
};

export default ClientList;