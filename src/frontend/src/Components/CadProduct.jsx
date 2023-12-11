import { useState} from "react";
import { useNavigate } from "react-router-dom";
import '../Style/cadProduct.css'
import Navbar from "../Layout/Navbar";
import'../Components/CampoTexto/CampoTexto'
import'../Components/Botao/Botao'
import axios from 'axios';
import CampoTexto from "../Components/CampoTexto/CampoTexto";
import Botao from "../Components/Botao/Botao";

function CadProduct() {

  const navigate = useNavigate();
  const [nome, setNome] = useState('')
  const [pontos, setPontos] = useState('')


  const aoSalvar = (evento) => {
    evento.preventDefault()
    console.log('Form foi submetido => ', nome, pontos)

    let teste = axios.post("http://localhost:8080/produtos",
        {
          nomeProduto: nome,
          pontos: pontos
        },
        {
            headers: {
                "Authorization": 'Bearer ' + localStorage.getItem('token')
            }
        }
    ).then((response) => {
        console.log(response.data);
        

    }).catch(error => {
        console.log(error);
    });


    navigate("/Product")

}


  return (
      <div>
          <Navbar />
          <section className="formulario">
              <form onSubmit={aoSalvar}>
                  <h2>cadastre seu produto</h2>

                  <CampoTexto
                      obrigatorio={true}
                      label="Nome"
                      placeholderModificada="Nome produto..."
                      valor={nome}
                      aoAlterado={valor => setNome(valor)}
                  />


                  <CampoTexto
                      obrigatorio={true}
                      label="Pontos"
                      placeholderModificada="Pontos produto"
                      valor={pontos}
                      aoAlterado={valor => setPontos(valor)}
                  />


                  <Botao>
                      Cadastrar
                  </Botao>

              </form>
          </section>
      </div>
  )
}
export default CadProduct