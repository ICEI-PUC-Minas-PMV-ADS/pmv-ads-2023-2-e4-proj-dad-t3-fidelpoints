import '../Style/cadProduct.css'
import { useState } from 'react';
//import { json } from "react-router-dom";
//import foto from '../Image/fotoCracha.jpg'

function EditProduct(props){
    
    const[image, setImage] = useState();
    const[postProduct, setPostProduct] = useState({'ID': 15 , 'name':"", 'description':"",'image':Blob, 'points':""});
    
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
       props.productT(postProduct);
    }

    const cancel = ()=> {
        Array.from(document.querySelectorAll('input')).map(input =>
            input.value = ""
        )
        setImage(null);
        setPostProduct("");
    }
    return(
        <div className="layoutProduct"><h2>Edição de produto</h2>
            <div className="editProduct">
                
                <div className="content">
                    <form className="image" onSubmit={uploadFile}>
                        <div  className='divId'>
                        <h4 >ID:</h4>
                        <h5 className='numberEdit' type="text" name="idProduct">{postProduct.ID}</h5>
                        </div>
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
                            <img src="#" width="150" height="100" alt="Carregue uma imagem válida"/>}
                    
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
export default EditProduct