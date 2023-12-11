
import Botao from '../Botao/Botao'
import { useState } from 'react'
import CampoTexto from '../CampoTexto/CampoTexto'
import './CadastroLojista.css'
import Navbar from '../../Layout/Navbar'

const CadastroLojista = () => {


    const [nome, setNome] = useState('')
    const [email, setEmail] = useState('')
    const [cnpj, setCnpj] = useState('')
    const [senha, setSenha] = useState('')
    const [confirmarSenha, setConfirmarSenha] = useState('')



    const aoSalvar = (evento) => {
        evento.preventDefault()
        console.log('Form foi submetido => ', nome, email, cnpj, senha, confirmarSenha)
    }

    return (
        <div>
            <Navbar/>
            <section className="formulario">
                <form onSubmit={aoSalvar}>
                    <h2>Preencha os campos a baixo para se conectar</h2>
                    <CampoTexto
                        obrigatorio={true}
                        label="Nome"
                        placeholderModificada="Digite seu nome..."
                        valor={nome}
                        aoAlterado={valor => setNome(valor)}
                    />

                    <CampoTexto
                        obrigatorio={true}
                        label="Cnpj"
                        placeholderModificada="Digite seu CNPJ..."
                        valor={cnpj}
                        aoAlterado={valor => setCnpj(valor)}
                    />

                    <CampoTexto
                        obrigatorio={true}
                        label="Email"
                        placeholderModificada="Digite seu email..."
                        valor={email}
                        aoAlterado={valor => setEmail(valor)}
                    />

                    <CampoTexto
                        obrigatorio={true}
                        label="Senha"
                        placeholderModificada="Digite sua Senha..."
                        valor={senha}
                        aoAlterado={valor => setSenha(valor)}
                    />

                    <CampoTexto
                        obrigatorio={true}
                        label="Confirmar Senha"
                        placeholderModificada="Confirme sua Senha..."
                        valor={confirmarSenha}
                        aoAlterado={valor => setConfirmarSenha(valor)}
                    />

                    <Botao>
                        Cadastrar
                    </Botao>
                </form>
            </section>
        </div>
    )
}

export default CadastroLojista