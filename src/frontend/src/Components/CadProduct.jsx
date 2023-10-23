import { useState,/* useEffect */ } from "react";
import '../Style/cadProduct.css'
//import Product from "./Product";
//import { json } from "react-router-dom";
//import foto from '../Image/fotoCracha.jpg'

function CadProduct(props){
    
    const[image, setImage] = useState();
    const[postProduct, setPostProduct] = useState({'name':"", 'description':"",'image':Blob, 'points':""});
    
    const preview = (e) => {
        setImage(e.target.files[0])
        if(e.target.getAttribute('name') === "nameImage"){
            setPostProduct({
                'name':postProduct.name, 
                'description':postProduct.description,
                'image': e.target.files[0],
                'points':postProduct.points
            })
        //    console.log(postProduct);
        }
        
    }
    function fillObject(e){
        e.preventDefault();
    //    console.log(postProduct)
    //    console.log(e.target.getAttribute('name'))
    
           if(e.target.getAttribute('name') === "nameProduct"){
                setPostProduct({'name':e.target.value, 'description':postProduct.description,'image': postProduct.image, 'points':postProduct.points})
           }else if(e.target.getAttribute('name') === "description"){
                setPostProduct({'name':postProduct.name, 'description':e.target.value,'image': postProduct.image, 'points':postProduct.points})
           }else if(e.target.getAttribute('name') === "points"){
                setPostProduct({'name':postProduct.name, 'description':postProduct.description,'image': postProduct.image, 'points':e.target.value})
           }
        //   console.log(postProduct)
        
    }
    const uploadFile = e =>{
       //Aqui será o axios
       e.preventDefault()
       console.log(postProduct)
    //   props.productT(postProduct);
       const stringProduct = JSON.stringify(postProduct)
       console.log(stringProduct)
    }

    const cancel = ()=> {
        Array.from(document.querySelectorAll('input')).map(input =>
            input.value = ""
        )
        setImage(null);
        setPostProduct("");
    }
    return(
        <div className="layoutProduct"><h2>Cadastro de produtos</h2>
            <div className="cadProduct">
                
                <div className="content">
                    <form className="image" onSubmit={uploadFile}>
                        <input name="nameProduct" 
                            className="nameProduct"
                            type="text" 
                            placeholder="Digite Nome do Produto"
                            id="nameProduct"
                            onChange={(e) =>fillObject(e)}/>
                        <label htmlFor="">Selecione arquivo de imagem:</label>
                        <input name="nameImage" 
                            className="file" 
                            type="file" id="file" 
                            onChange={preview} />
                        {image ? <img src={URL.createObjectURL(image)} width="150" height="200" alt="Nao carregou"/> : 
                            <img src="#" width="150" height="200" alt="Carregue uma imagem válida"/>}
                    
                        <textarea placeholder="Descrição:" name="description" id="description" cols="25" rows="4" onChange={(e) =>fillObject(e)}></textarea>
                        <div className="point">
                        <label>Pontos:</label>
                        <input type="number" name="points" id="points" onChange={(e) =>fillObject(e)} />
                        </div>
                        <div className="buttons">
                        <button type="button" onClick={cancel}>Cancelar</button>
                        <button type="submit">Confirmar</button>
                        </div>
                    </form>
                </div>
                
                
            </div>
        </div>
    )
}
export default CadProduct