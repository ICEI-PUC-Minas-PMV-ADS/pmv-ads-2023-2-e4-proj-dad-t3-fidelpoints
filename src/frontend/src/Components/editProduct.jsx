import '../Style/cadProduct.css'
import { useState } from 'react';
import '../Layout/Navbar'
//import { json } from "react-router-dom";
//import foto from '../Image/fotoCracha.jpg'

function EditProduct(props){
    
    const [productId, setProductId] = useState(15);
    const [postProduct, setPostProduct] = useState({
      'ID': productId,
      'name': "",
      'description': "",
      'points': ""
    });
  
    const fillObject = (e) => {
      e.preventDefault();
      const { name, value } = e.target;
      setPostProduct(prevProduct => ({
        ...prevProduct,
        [name]: value,
      }));
    };
  
    const uploadFile = (e) => {
      e.preventDefault();
      console.log(postProduct);
      props.productT(postProduct);
    };
  
    const cancel = () => {
      Array.from(document.querySelectorAll('input')).map(input =>
        input.value = ""
      );
      setPostProduct("");
    };
  
    return (
      <div className="layoutProduct">
        <h2>Edição de produto</h2>
        <div className="editProduct">
          <div className="content">
            <form onSubmit={uploadFile}>
              <div className='divId'>
                <h4>ID:</h4>
                <h5 className='numberEdit' type="text" name="idProduct">{productId}</h5>
              </div>
              <input
                name="nameProduct"
                className="nameProduct"
                type="text"
                placeholder="Digite Nome do Produto"
                onChange={(e) => fillObject(e)}
              />
              <div className="point">
                <label>Pontos:</label>
                <input
                  type="number"
                  name="points"
                  id="points"
                  onChange={(e) => fillObject(e)}
                />
              </div>
              <div className="buttons">
                <button type="button" onClick={cancel}>Cancelar</button>
                <button type="submit">Confirmar</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
}
export default EditProduct