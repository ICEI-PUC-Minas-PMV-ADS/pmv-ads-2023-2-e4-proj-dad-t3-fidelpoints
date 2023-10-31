import { useState } from 'react';
import '../Style/client.css';
//import { Link } from 'react-router-dom';
//import {fs as fs} from 'node:fs'
//import CadClient from './CadClient';

function Loged() {
  
    const [pedidos,/* setClient*/] = useState([
        {'name':"Edglei", 'Product':"Só testando pra ver",'clientPoints': "200", 'points':"200"},
        {'name':"Edivania", 'Product':"Só testando pra ver",'clientPoints': "150", 'points':"400"},
        {'name':"Ana", 'Product':"Só testando pra ver",'clientPoints': "800", 'points':"40"}
        ]);
/*    function table(){
    //    fs.readFile ("Client.json", (err, data) => setClient(JSON.parse(data)))
       return(
        <h2>Não sei</h2>
       )


       

    }*/
    
    return(
        <div className="layoutClient">
            <h2>Lista de pedidos</h2>
            <div className="client">
                <ul className='listClient'>
                    <li className='list'>
                                <li className='cabecalho'> Cliente</li>
                                <li className='cabecalho'> Pontos Clientes</li> 
                                <li className='cabecalho'> Produtos</li>
                                <li className='cabecalho'> Pontos Protudo:</li>
                                
                    </li>
                    <hr />
                    {pedidos ?
                        pedidos.map(p =>
                            <li className='list'>
                                <li> {p.name} </li>
                                <li>{p.clientPoints}</li> 
                                <li> {p.Product} </li>
                                <li> {p.points}</li>
                                
                            </li>)
                        : <h2>Cliente não cadastrado</h2>
                    }
                </ul>
            </div>
            
        </div>
    )
}

export default Loged;

