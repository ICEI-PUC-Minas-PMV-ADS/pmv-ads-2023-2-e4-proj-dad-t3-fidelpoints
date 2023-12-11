
import Botao from '../Components/Botao/Botao'
import { useState } from 'react'
import CampoTexto from '../Components/CampoTexto/CampoTexto'
import '../Style/RecuperarSenha.css'

const RecuperarSenha = () => {


    const[email, setEmail] = useState('')


    const aoSalvar = (evento) => {
        evento.preventDefault()
        console.log('Form foi submetido => ', email, )
    }

    return(
        <section className="formulario">
            <form onSubmit={aoSalvar}>
            <h2>Vamos te ajudar!</h2>
            <p>Você receberá um link no seu e-mail cadastrado para alterar a senha</p>
            <CampoTexto 
                obrigatorio = {true} 
                label = "Email" 
                placeholderModificada= "Digite seu Email..."
                valor = {email}
                aoAlterado = {valor => setEmail (valor)}
            />

            <Botao>
                Redefinir
            </Botao>
            </form>
        </section>
    )
}

export default RecuperarSenha