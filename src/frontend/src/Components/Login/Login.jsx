
import { useNavigate } from "react-router-dom";
import Botao from '../Botao/Botao'
import { useState } from 'react'
import CampoTexto from '../CampoTexto/CampoTexto'
import axios from "axios";
import './Login.css'


const Login = () => {

    


    const[email, setEmail] = useState('')
    const[senha, setSenha] = useState('')
    const[ok, setOk] = useState(false)
    const navigate = useNavigate();

    


    const aoSalvar = async (evento) => {
        evento.preventDefault()      
        console.log('Form foi submetido => ', email, senha, )
        setOk (true)
        console.log("Entrou ui e ok é" + ok)



        await axios.post("http://localhost:8080/auth/lojistas", {
            login: email,
            senha: senha
          })
          .then((response) => {
            //console.log(response.data.senha);

            localStorage.setItem('token', response.data.senha);
            localStorage.getItem('token');

          }).catch(error => {
                console.log(error);
                localStorage.clear();
          });;

   
          let tokenStorage = localStorage.getItem('token');


          console.log('teste: ' + tokenStorage);
        if(tokenStorage != null){
            navigate("/Product")
            console.log("passou aqui "+ ok)
            
        }
        
    }

    return(
        <section className="formulario">
            <form onSubmit={aoSalvar}>
            <h2>Preencha os campos a baixo para se conectar</h2>
            <CampoTexto 
                obrigatorio = {true} 
                label = "Email" 
                placeholderModificada= "Digite seu Email..."
                valor = {email}
                aoAlterado = {valor => setEmail (valor)}
            />

            <CampoTexto 
                obrigatorio = {true} 
                label = "Senha" 
                placeholderModificada= "Digite sua senha..."
                valor = {senha}
                aoAlterado = {valor => setSenha (valor)}
                />

            <Botao onClick = {onclick}>
            Conectar
            </Botao>
            </form>
        </section>
    )
}

export default Login