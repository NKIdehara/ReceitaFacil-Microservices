import axios from 'axios';
import * as bootstrap from 'bootstrap';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { BACKEND } from '../App';
import { user } from '../Firebase';
import Spinner from '../layout/Spinner';

export default function Items() {
    let navigate = useNavigate();

    const [items, setItems] = useState([]);
    useEffect( () => {
        loadItems();
    }, []);
    const loadItems = async() => {
        const result = await axios.get(BACKEND.concat("/item"));
        setItems(result.data);
        setEspera(false);
    }
    async function delItem(id) {
        let success = true;
        setEspera(true);
        try {
            await axios.delete(BACKEND.concat("/item/", id));
        } catch(err) {
            if(err.code === 'ERR_BAD_RESPONSE') {
                success = false;
                window.alert("Não foi possível excluir item!\nItem está associado à uma receita.");
            }
        }
        if(success) {
            const novaLista = items.filter(item => item.id !== id);
            setItems(novaLista);
        }
        setEspera(false);
    }

    const [medidas, setMedidas] = useState([]);
    useEffect(() => {
        loadMedidas();
    }, []);
    const loadMedidas = async () => {
        const result = await axios.get(BACKEND.concat("/medida"));
        setMedidas(result.data);
    }

    const initialState = {
        id: 0,
        descricao: "",
        preco: "",
        medida: ""
    }
    const [item, setItem] = useState(initialState);

    const onItemChange = (e) => {
        setItem({...item, [e.target.name]:e.target.value});
    }

    const onSubmitItem = async (e) => {
        if(item.descricao !== "" && item.preco !== "" && item.medida !== "") {
            e.preventDefault();
            setEspera(true);
            console.log(item);

            const result = await axios.post(BACKEND.concat("/item/"), {
                "descricao": item.descricao,
                "preco": item.preco,
                "medida": { "id": item.medida }
            });
            setItem(initialState);
            loadItems();
            setEspera(false);
        } else {
            let toast = new bootstrap.Toast(document.getElementById('Toast'));
            toast.show();
        }
}

    const {descricao, preco, medida} = item;
    const [espera, setEspera] = useState(true);

    if(user.isNull) { return navigate("/") }
    return (
        <div className="container">
            <div className="py-4 ">

                <div className="form-group row border border-primary rounded">
                    <div className="form-group row my-2">
                        <label htmlFor="nome" className="col-sm-2 my-2 col-form-label">Descrição:</label>
                        <div className="col-sm-10">
                            <input type={"text"} className="form-control my-2" required placeholder="Descrição do Item" name="descricao" value={descricao} onChange={(e) => onItemChange(e)} />
                        </div>
                    </div>

                    <div className="form-group row my-2">
                        <label htmlFor="quantidade" className="col-sm-2 col-form-label">Preço:</label>
                        <div className="col-sm-10">
                            <input type={"number"} className="form-control" placeholder="Preço" name="preco" value={preco} onChange={(e) => onItemChange(e)} />
                        </div>
                    </div>

                    <div className="form-group row my-2">
                        <label htmlFor="medida" className="col-sm-2 col-form-label">Unidade de medida:</label>
                        <div className="col-sm-10">
                            <select className="form-select" aria-label="Medida" name="medida" value={medida} onChange={(e) => onItemChange(e)} >
                                <option value={0}></option>
                                {medidas.map((m) => (<option value={m.id}>{m.nome}</option>))}
                            </select>
                        </div>
                    </div>

                    <div id="Toast" className="toast align-items-center text-bg-danger border-0 position-absolute top-50 start-50 translate-middle" role="alert" aria-live="assertive" aria-atomic="true">
                        <div className="d-flex">
                            <div className="toast-body">Item inválido!</div>
                            <button type="button" className="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                        </div>
                    </div>

                    <div className="">
                        <div className="my-2 float-none">
                            <button type="button" className="btn btn-outline-primary" onClick={(e) => onSubmitItem(e)}>➕</button>
                        </div>
                    </div>
                </div>
            </div>

            <div className="py-4 ">
                <table className="table table-hover shadow">
                <thead className="table-secondary">
                    <tr>
                    <th scope="col">Descrição</th>
                    <th scope="col">Preço</th>
                    <th scope="col">Medida</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody className="text-center">
                    {
                        items.map((item, id) => (
                            <tr>
                            <td className="text-start">{item.descricao}</td>
                            <td>{item.preco}</td>
                            <td>{item.medida.nome}</td>
                            <td></td>
                            <td><button type="button" className="btn btn-light" onClick={() => delItem(item.id)}>❌</button></td>
                            </tr>
                        ))
                    }
                </tbody>
                </table>
                {espera && <Spinner />}
            </div>
        </div>
    )
}