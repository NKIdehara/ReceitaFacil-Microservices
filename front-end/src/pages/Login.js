import * as bootstrap from 'bootstrap';
import { createUserWithEmailAndPassword, signInWithEmailAndPassword } from 'firebase/auth';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { auth, user } from '../Firebase';
import Spinner from '../layout/Spinner';
import ic_cook from '../resources/images/ic_cook.png';

const Login = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [message, setMessage] = useState('')

    const state = { button: 0 };

    const entrar = (e) => {
        e.preventDefault();
        setEspera(true);
        if (email === '' || senha === '') {
            setMessage("Email ou senha inválido!");
            let toast = new bootstrap.Toast(document.getElementById('Toast'));
            toast.show();
            setEspera(false);
        } else {
            if (state.button === 1) {
                signInWithEmailAndPassword(auth, email, senha)
                    .then(async (userCredential) => {
                        user.setUID(userCredential.user.uid);
                        navigate("/home");
                    })
                    .catch((error) => {
                        setEspera(false);
                        setMessage(error.message);
                        let toast = new bootstrap.Toast(document.getElementById('Toast'));
                        toast.show();
                    });
            }
            if (state.button === 2) {
                createUserWithEmailAndPassword(auth, email, senha)
                    .then((userCredential) => {
                        user.setUID(userCredential.user.uid);
                        navigate("/home");
                    })
                    .catch((error) => {
                        setEspera(false);
                        setMessage(error.message);
                        let toast = new bootstrap.Toast(document.getElementById('Toast'));
                        toast.show();
                    });
            }
        }
    }

    const [espera, setEspera] = useState(false);

    // let toast = new bootstrap.Toast(document.getElementById('Toast'));
    
    return (
        <div className="container-fluid">
            <div className="my-4">
                <h1>Receita Fácil</h1>
                <img src={ic_cook} className="w-25 m-1" style={{'maxWidth': '300px'}} alt=""/>
            </div>

            <div className="container">
                <form onSubmit={entrar}>
                    <div className="form-group row my-3">
                        <div className="col"></div>
                        <label for="usuario" className="col-sm-1 col-form-label">Usuário:</label>
                        <div className="col-sm">
                            <input type={"email"} className="form-control" placeholder="e-mail" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        </div>
                        <div className="col"></div>
                    </div>
                    <div className="form-group row my-3">
                        <div className="col"></div>
                        <label for="senha" className="col-sm-1 col-form-label">Senha:</label>
                        <div className="col-sm">
                            <input type={"password"} className="form-control" placeholder="senha" name="senha" value={senha} onChange={(e) => setSenha(e.target.value)} />
                        </div>
                        <div className="col"></div>
                </div>
                    {espera && <Spinner />}
                    <button type="submit" className="btn btn-outline-primary m-3 btn-lg" onClick={() => (state.button = 1)} >Login</button>
                    <button type="submit" className="btn btn-outline-primary m-3 btn-lg" onClick={() => (state.button = 2)} >Novo</button>
                </form>

                <div id="Toast" className="toast align-items-center text-bg-danger border-0 position-absolute top-50 start-50 translate-middle" role="alert" aria-live="assertive" aria-atomic="true">
                    <div className="d-flex">
                        <div className="toast-body">
                            {message}
                        </div>
                        <button type="button" className="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                </div>

            </div>
        </div>
    )
}

export default Login