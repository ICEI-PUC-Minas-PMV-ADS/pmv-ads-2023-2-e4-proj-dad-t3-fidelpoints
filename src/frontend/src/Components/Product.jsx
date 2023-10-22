import { useState } from 'react';
import '../Style/produtos.css';
import { Link } from 'react-router-dom';
import {fs as fs} from 'node:fs'
//import CadProduct from './CadProduct';

function Product({postProduto}){
    const [product,/* setProduct*/] = useState([
        {'name':"Edglei", 'description':"Só testando pra ver",'image':Blob, 'points':"200"},
        {'name':"Edivania", 'description':"Só testando pra ver",'image':Blob, 'points':"200"},
        {'name':"Ana", 'description':"Só testando pra ver",'image':Blob, 'points':"200"}
        ]);
/*    function table(){
    //    fs.readFile ("Product.json", (err, data) => setProduct(JSON.parse(data)))
       return(
        <h2>Não sei</h2>
       )


       

    }*/
    
    return(
        <div className="layoutProdutos">
            <h2>Lista de produtos</h2>
            <div className="produtos">
                <ul className='listProduct'>
                <li className='list'>
                                <li>Foto</li>
                                <li> Nome</li> 
                                <li> Descrição:</li>
                                <li> Pontos:</li>
                                <li> Editar</li>
                            </li>
                    {product ?
                        product.map(p =>
                            <li className='list'>
                                <li><img className='imgList' src= "#" alt='#'/></li>
                                <li> {p.name} </li> 
                                <li> {p.description} </li>
                                <li> {p.points}</li>
                                <Link to={'/EditProduct'}>Edite</Link>
                            </li>)
                        : <h2>Produtos não cadastrado</h2>
                    }
                </ul>
            </div>
            
        </div>
    )
}

export default Product;