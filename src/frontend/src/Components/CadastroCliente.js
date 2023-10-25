
import Botao from '../Components/Botao'
import { useState } from 'react'
import CampoTexto from '../Components/CampoTexto'
import '../Style/CadastroCliente.css'

const CadastroCliente = () => {


    const [nome, setNome] = useState('')
    const [celular, setCelular] = useState('')
    const [pontos, setPontos] = useState('')




    const aoSalvar = (evento) => {
        evento.preventDefault()
        console.log('Form foi submetido => ', nome, celular, pontos)
    }

    return (
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
                    valor={celular}
                    aoAlterado={valor => setPontos(valor)}
                />

                <Botao>
                    Pontuar
                </Botao>

            </form>
        </section>
    )
}

export default CadastroCliente