import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { If, Then } from 'react-if';
import axios from 'axios';
import { user } from '../Firebase';
import Spinner from '../layout/Spinner';
import { BACKEND } from '../App';

export default function Receitas() {
    
    const [receitas, setReceitas] = useState([]);
    useEffect( () => {
        loadReceitas();
    }, []);

    const loadReceitas = async() => {
        const result = await axios.get(BACKEND.concat("/receita/usuario/", user.getUID));
        setReceitas(result.data);
        setEspera(false);
    }

    async function deleteReceita(id) {
        setEspera(true);
        await axios.delete(BACKEND.concat("/receita/", id));
        const novaLista = receitas.filter(receita => receita.id !== id);
        setReceitas(novaLista);
        setEspera(false);
    }

    const [espera, setEspera] = useState(true);    

    if(!user.isNull) {
        return (
            <div className="container">
                <div className="py-4 ">

                    <table className="table table-hover shadow">
                    <thead className="table-secondary">
                        <tr>
                        <th scope="col">#</th>
                        <th scope="col">Foto</th>
                        <th scope="col">Data</th>
                        <th scope="col">Nome da Receita</th>
                        <th scope="col">Ingredientes</th>
                        <th scope="col">Preparo</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody className="text-start">
                        {
                            receitas.map((receita, id_receita) => (
                                <tr>
                                <th scope="row" key={id_receita}>{id_receita+1}</th>
                                <td><img src={receita.figura} style={{'maxWidth': '100px'}} alt=""/></td>
                                <td>{receita.data_receita}</td>
                                <td>{receita.nome}</td>
                                <td style={{whiteSpace: "pre-wrap"}}>{receita.ingredientes}</td>
                                <td style={{whiteSpace: "pre-wrap"}}>{receita.preparo}</td>
                                <If condition={user.getUID !== 0}><Then>
                                    <td><Link className="btn btn-light m-1" to="/edtReceita" state={{ _id: receita.id, _nome: receita.nome, _ingredientes: receita.ingredientes, _preparo: receita.preparo, _data_receita: receita.data_receita, _usuario: receita.usuario, _figura: receita.figura }}>📝</Link></td>
                                </Then></If>
                                <If condition={user.getUID !== 0}><Then>
                                    <td><button type="button" className="btn btn-light" onClick={() => deleteReceita(receita.id)}>❌</button></td>
                                </Then></If>
                                </tr>
                            ))
                        }
                    </tbody>
                    </table>
                    {espera && <Spinner />}
                </div>

                <div className="float-end">
                    <Link className="btn btn-outline-dark m-1" to="/addReceita">Nova Receita</Link>
                    <Link className="btn btn-outline-dark m-1" to="/home">Cancelar</Link>
                </div>
            </div>
       )
    }
}