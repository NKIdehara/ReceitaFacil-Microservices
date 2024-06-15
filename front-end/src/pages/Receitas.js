import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { If, Then } from 'react-if';
import axios from 'axios';
import { format } from "date-fns";
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

    function formatDate(date) {
        if(date == null) return "---";
        const utcDate = new Date(date);
        let localDate = new Date(utcDate.getTime() + utcDate.getTimezoneOffset() * 60000);
        if(utcDate.getMinutes()+utcDate.getSeconds() > 0) localDate = utcDate;
        return format(localDate, "yyyy-MM-dd");
    }

    if(!user.isNull) {
        return (
            <div className="container">
                <div className="py-2">

                    <table className="table table-hover shadow">
                    <thead className="table-secondary">
                        <tr>
                        <th scope="col">#</th>
                        <th scope="col">Foto</th>
                        <th scope="col">Nome da Receita</th>
                        <th scope="col">Ingredientes</th>
                        <th scope="col">Preparo</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col">Histórico</th>
                        </tr>
                    </thead>
                    <tbody className="text-start">
                        {
                            receitas.map((receita, id_receita) => (
                                <tr>
                                <th scope="row" key={id_receita}>{id_receita+1}</th>
                                <td><img src={receita.figura} style={{'maxWidth': '100px'}} alt=""/></td>
                                <td>{receita.nome}</td>

                                <td>{receita.ingredientes.map((ingrediente) => (
                                    <tr>
                                    <td>- {ingrediente.quantidade} {ingrediente.medida} de {ingrediente.item}</td>
                                    </tr>
                                ))}</td>

                                <td style={{whiteSpace: "pre-wrap"}}>{receita.preparo}</td>
                                <If condition={user.getUID !== 0}><Then>
                                    <td><Link className="btn btn-light m-1" to="/edtReceita" state={{ _id: receita.id, _nome: receita.nome, _ingredientes: receita.ingredientes, _preparo: receita.preparo, _usuario: receita.usuario, _figura: receita.figura }}>📝</Link></td>
                                </Then></If>
                                <If condition={user.getUID !== 0}><Then>
                                    <td><button type="button" className="btn btn-light" onClick={() => deleteReceita(receita.id)}>❌</button></td>
                                </Then></If>

                                <td>
                                    <tr><td>criado: {formatDate(receita.createDate)}</td></tr>
                                    <tr><td>modificado: {formatDate(receita.lastModifiedDate)}</td></tr>
                                </td>

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