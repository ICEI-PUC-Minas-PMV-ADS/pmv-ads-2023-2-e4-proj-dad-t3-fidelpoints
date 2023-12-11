import { useState,/* useEffect */ } from "react";
import '../CadClients.css'
//import Client from "./Client";
//import { json } from "react-router-dom";
//import foto from '../Image/fotoCracha.jpg'

function CadClient(props){
    
    const[image, setImage] = useState();
    const[postClient, setPostClient] = useState({'name':"", 'description':"",'image':Blob, 'points':""});
    
    const preview = (e) => {
        setImage(e.target.files[0])
        if(e.target.getAttribute('name') === "nameImage"){
            setPostClient({
                'name':postClient.name, 
                'description':postClient.description,
                'image': e.target.files[0],
                'points':postClient.points
            })
        //    console.log(postClient);
        }
        
    }
    function fillObject(e){
        e.preventDefault();
    //    console.log(postClient)
    //    console.log(e.target.getAttribute('name'))
    
           if(e.target.getAttribute('name') === "nameClient"){
                setPostClient({'name':e.target.value, 'description':postClient.description,'image': postClient.image, 'points':postClient.points})
           }else if(e.target.getAttribute('name') === "description"){
                setPostClient({'name':postClient.name, 'description':e.target.value,'image': postClient.image, 'points':postClient.points})
           }else if(e.target.getAttribute('name') === "points"){
                setPostClient({'name':postClient.name, 'description':postClient.description,'image': postClient.image, 'points':e.target.value})
           }
        //   console.log(postClient)
        
    }
    const uploadFile = e =>{
       //Aqui será o axios
       e.preventDefault()
       console.log(postClient)
    //   props.clientT(postClient);
       const stringClient = JSON.stringify(postClient)
       console.log(stringClient)
    }

    const cancel = ()=> {
        Array.from(document.querySelectorAll('input')).map(input =>
            input.value = ""
        )
        setImage(null);
        setPostClient("");
    }
    return(
        <div className="layoutClient"><h2>Cadastro de produtos</h2>
            <div className="cadClient">
                
                <div className="content">
                    <form className="image" onSubmit={uploadFile}>
                        <input name="nameClient" 
                            className="nameClient"
                            type="text" 
                            placeholder="Digite Nome do Produto"
                            id="nameClient"
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
export default CadClient