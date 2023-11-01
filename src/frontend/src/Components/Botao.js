import '../Style/Botao.css'

const Botao = (props) => {
    const logOk = () => {
        localStorage.setItem("logOk", "Ok")
        console.log(localStorage.getItem("logOk"))
    }

    return (<button type='submit' className='botao' onClick={logOk}>
        {props.children}
    </button>)

}

export default Botao