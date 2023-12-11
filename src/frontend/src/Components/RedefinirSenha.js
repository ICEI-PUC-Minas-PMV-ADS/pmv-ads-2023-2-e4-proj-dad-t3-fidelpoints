import Botao from '../Components/Botao/Botao'
import { useState } from 'react'
import CampoTexto from '../Components/CampoTexto/CampoTexto'
import '../Style/RedefinirSenha.css'

const RedefinirSenha = () => {


    const [redefinirSenha, setRedefinirSenha] = useState('')
    const [novaSenha, setNovaSenha] = useState('')


    const aoSalvar = (evento) => {
        evento.preventDefault()
        console.log('Form foi submetido => ', redefinirSenha, novaSenha)
    }

    return (
        <section className="formulario">
            <form onSubmit={aoSalvar}>
                <h2>Digite e Confirme sua Nova Senha</h2>
                <CampoTexto
                    obrigatorio={true}
                    label="Nova Senha"
                    placeholderModificada="Digite sua nova senha..."
                    valor={redefinirSenha}
                    aoAlterado={valor => setRedefinirSenha(valor)}
                />

                <CampoTexto
                    obrigatorio={true}
                    label="Repita a Senha"
                    placeholderModificada="Confirme sua nova senha..."
                    valor={novaSenha}
                    aoAlterado={valor => setNovaSenha(valor)}
                />

                <Botao>
                    Redefinir
                </Botao>
            </form>
        </section>
    )
}

export default RedefinirSenha