
import Botao from '../Botao/Botao'
import { useNavigate } from "react-router-dom";
import { useState } from 'react'
import CampoTexto from '../CampoTexto/CampoTexto'
import './CadastroCliente.css'
import Navbar from '../../Layout/Navbar'
import axios from 'axios';


const CadastroCliente = () => {


    const [nome, setNome] = useState('')
    const [celular, setCelular] = useState('')
    const [pontos, setPontos] = useState('')
    const [email, setEmail] = useState('')
    const navigate = useNavigate();




    const aoSalvar = (evento) => {
        evento.preventDefault()
        console.log('Form foi submetido => ', nome, celular, pontos)

        let teste = axios.post("http://localhost:8080/pontos",
            {
                emailCliente: email,
                celular: celular,
                nomeCliente: nome,
                quantidadePontos: pontos
            },
            {
                headers: {
                    "Authorization": 'Bearer ' + localStorage.getItem('token')
                }
            }
        ).then((response) => {
            console.log(response.data);

            navigate("/Client")

        }).catch(error => {
            console.log(error);
        });;

    }


    return (
        <div>
            <Navbar />
            <section className="formulario">

                <form onSubmit={aoSalvar}>
                    <h2>Preencha os campos a baixo com os dados do cliente</h2>
                    <CampoTexto
                        obrigatorio={true}
                        label="Nome"
                        placeholderModificada="Nome Cliente..."
                        valor={nome}
                        aoAlterado={valor => setNome(valor)}
                    />

                    <CampoTexto
                        obrigatorio={true}
                        label="Celular"
                        placeholderModificada="Celular Cliente"
                        valor={celular}
                        aoAlterado={valor => setCelular(valor)}
                    />

                    <CampoTexto
                        obrigatorio={true}
                        label="Pontos"
                        placeholderModificada="Pontos Cliente"
                        valor={pontos}
                        aoAlterado={valor => setPontos(valor)}
                    />

                    <CampoTexto
                        obrigatorio={true}
                        label="Email"
                        placeholderModificada="Email Cliente"
                        valor={email}
                        aoAlterado={valor => setEmail(valor)}
                    />

                    <Botao>
                        Pontuar
                    </Botao>

                </form>
            </section>
        </div>
    )
}

export default CadastroCliente