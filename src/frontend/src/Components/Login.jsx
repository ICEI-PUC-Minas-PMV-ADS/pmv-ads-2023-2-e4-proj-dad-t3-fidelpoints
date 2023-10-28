
import Botao from '../Components/Botao'
import { useState } from 'react'
import CampoTexto from '../Components/CampoTexto'
import '../Style/Login.css'

const Login = () => {


    const[nome, setNome] = useState('')
    const[email, setEmail] = useState('')
    const{ok, setOk} =useState(false)




    const aoSalvar = (evento) => {
        evento.preventDefault()
        setOk(true)
        console.log('Form foi submetido => ', nome, email, )
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