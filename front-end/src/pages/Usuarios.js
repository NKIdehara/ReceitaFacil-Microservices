import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Else, If, Then } from 'react-if';
import { Link, useNavigate } from 'react-router-dom';
import { BACKEND } from '../App';
import { user } from '../Firebase';
import Spinner from '../layout/Spinner';

export default function Usuarios() {
    let navigate = useNavigate();

    const [usuarios, setUsuarios] = useState([]);
    useEffect( () => {
        loadUsuarios();
    }, []);

    const loadUsuarios = async() => {
        const result = await axios.get(BACKEND.concat("/usuario"));
        setUsuarios(result.data);
        setEspera(false);
    }

    async function delUsuario(id) {
        setEspera(true);
        await axios.delete(BACKEND.concat("/usuario/", id));
        const novaLista = usuarios.filter(usuario => usuario.id !== id);
        setUsuarios(novaLista);
        setEspera(false);
    }

    const [espera, setEspera] = useState(true);

    if(user.isNull) { return navigate("/") }
    return (
        <div className="container">
            <div className="py-4 ">
                <table className="table table-hover shadow">
                <thead className="table-secondary">
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">UID</th>
                    <th scope="col">e-mail</th>
                    <th scope="col">Tipo de Acesso</th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody className="text-center">
                    {
                        usuarios.map((usuario, id) => (
                            <tr>
                            <th scope="row" key={usuario.id}>{usuario.id}</th>
                            <td>{usuario.uid}</td>
                            <td>{usuario.email}</td>
                            <td>{usuario.acesso}</td>
                            <If condition={(user.UID !== usuario.uid)}>
                                <Then><td><button type="button" className="btn btn-light" onClick={() => delUsuario(usuario.id)}>❌</button></td></Then>
                                <Else><Then><td></td></Then></Else>
                            </If>

                            </tr>
                        ))
                    }
                </tbody>
                </table>
                {espera && <Spinner />}
            </div>

            <div className="float-end">
                <Link className="btn btn-outline-dark m-1" to="/home">Cancelar</Link>
            </div>
        </div>
    )

}