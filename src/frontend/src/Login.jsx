
import { useNavigate } from "react-router-dom";
import Botao from '../Botao/Botao'
import { useState } from 'react'
import CampoTexto from '../CampoTexto/CampoTexto'
import './Login.css'

const Login = () => {


    const[nome, setNome] = useState('')
    const[email, setEmail] = useState('')
    const[ok, setOk] = useState(false)
    const navigate = useNavigate();




    const aoSalvar = (evento) => {
        evento.preventDefault()      
        console.log('Form foi submetido => ', nome, email, )
        setOk (true)
        console.log("Entrou aui e ok é" + ok)
        
        if(ok === true){
            navigate("/loged")
            console.log("passou aqui "+ ok)
            
        }
        
    }

    return(
        <section className="formulario">
            <form onSubmit={aoSalvar}>
            <h2>Preencha os campos a baixo para se conectar</h2>
            <CampoTexto 
                obrigatorio = {true} 
                label = "Nome" 
                placeholderModificada= "Digite seu nome..."
                valor = {nome}
                aoAlterado = {valor => setNome (valor)}
            />

            <CampoTexto 
                obrigatorio = {true} 
                label = "Email" 
                placeholderModificada= "Digite seu email..."
                valor = {email}
                aoAlterado = {valor => setEmail (valor)}
                />

            <Botao onClick = {onclick}>
            Conectar
            </Botao>
            </form>
        </section>
    )
}

export default Login