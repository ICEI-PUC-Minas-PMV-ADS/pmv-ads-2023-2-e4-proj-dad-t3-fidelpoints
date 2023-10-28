import { useState } from 'react';
import '../Style/client.css';
import { Link } from 'react-router-dom';
//import {fs as fs} from 'node:fs'
//import CadClient from './CadClient';

function Client({postProduto}){
    const [client,/* setClient*/] = useState([
        {'name':"Edglei", 'description':"Só testando pra ver",'image':Blob, 'points':"200"},
        {'name':"Edivania", 'description':"Só testando pra ver",'image':Blob, 'points':"200"},
        {'name':"Ana", 'description':"Só testando pra ver",'image':Blob, 'points':"200"}
        ]);
/*    function table(){
    //    fs.readFile ("Client.json", (err, data) => setClient(JSON.parse(data)))
       return(
        <h2>Não sei</h2>
       )


       

    }*/
    
    return(
        <div className="layoutClient">
            <h2>Lista de Clientes</h2>
            <div className="client">
                <ul className='listClient'>
                <li className='list'>
                                <li>Foto</li>
                                <li> Nome</li> 
                                <li> Descrição:</li>
                                <li> Pontos:</li>
                                <li> Editar</li>
                            </li>
                    {client ?
                        client.map(p =>
                            <li className='list'>
                                <li><img className='imgList' src= "#" alt='#'/></li>
                                <li> {p.name} </li> 
                                <li> {p.description} </li>
                                <li> {p.points}</li>
                                <Link to={'/EditClient'}>Edite</Link>
                            </li>)
                        : <h2>Cliente não cadastrado</h2>
                    }
                </ul>
            </div>
            
        </div>
    )
}

export default Client;