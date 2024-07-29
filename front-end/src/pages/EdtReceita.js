import axios from 'axios';
import * as bootstrap from 'bootstrap';
import { getDownloadURL, ref, uploadBytes } from 'firebase/storage';
import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { v4 } from 'uuid';
import { BACKEND } from '../App';
import { storage, user } from '../Firebase';
import Spinner from '../layout/Spinner';

export default function EdtReceita() {
    const location = useLocation();
    const { _id, _nome, _ingredientes, _preparo, _usuario, _figura } = location.state;

    let navigate = useNavigate();

    const [imgReceita, setImgReceita] = useState(null);
    const [receita, setReceita] = useState({
        nome: _nome,
        preparo: _preparo,
        usuario: _usuario,
        figura: _figura
    });


    const [items, setItems] = useState([]);
    useEffect(() => {
        loadItems();
    }, []);
    const loadItems = async () => {
        const result = await axios.get(BACKEND.concat("/item"));
        setItems(result.data);
    }
    const findItem = (id) => {
        let item = items.filter(i => i.id == id);
        if(item.length !== 0)
            return item[0].descricao;
    }

    const [medidas, setMedidas] = useState([]);
    useEffect(() => {
        loadMedidas();
    }, []);
    const loadMedidas = async () => {
        const result = await axios.get(BACKEND.concat("/medida"));
        setMedidas(result.data);
    }
    const [medidasFilter, setMedidasFilter] = useState([]);
    const loadMedidasFilter = (id) => {
        if(id == 0) {
            setMedidasFilter([]);
        } else {
            let itemSelecionado = items.filter(i => i.id == id);
            setMedidasFilter(medidas.filter(m => m.unidade === itemSelecionado[0].medida.unidade));
        }
    }
    const findMedida = (id) => {
        let medida = medidas.filter(m => m.id == id);
        if(medida.length !== 0)
            return medida[0].nome;
    }

    const [index, setIndex] = useState(99999);
    const [ingredientes, setIngredientes] = useState(_ingredientes);
    const addIngrediente = () => {
        const ingredienteNovo = {
            id: ingrediente.id,
            item: {id: ingrediente.item},
            quantidade: ingrediente.quantidade,
            medida: {id: ingrediente.medida}
        };
        setIngredientes([...ingredientes, ingredienteNovo]);
    };
    const delIngrediente = (id) => {
        const novaLista = ingredientes.filter(i => i.id !== id);
        setIngredientes(novaLista);
    };
    const initialState = {
        id: "",
        item: "",
        quantidade: "",
        medida: ""
    }
    const [ingrediente, setIngrediente] = useState(initialState);

    const {nome, preparo, usuario, figura} = receita;
    const {id, item, quantidade, medida} = ingrediente;

    const onReceitaChange = (e) => {
        setReceita({...receita, [e.target.name]:e.target.value});
    }
    const onIngredienteChange = (e) => {
        setIngrediente({...ingrediente, [e.target.name]:e.target.value});
        if(e.target.name === 'item') loadMedidasFilter(e.target.value);
    }

    const uploadImgReceita = () => {
        if(imgReceita == null) return null;
        const imageRef = ref(storage, `${imgReceita.name + v4()}`);
        return uploadBytes(imageRef, imgReceita).then((snapshot) => {
            return getDownloadURL(snapshot.ref).then((url) => { return url });
        });
    };

    const onSubmitReceita = async (e) => {
        e.preventDefault();
        setEspera(true);
        var imagem = await uploadImgReceita();
        if(imagem !== null) receita.figura = imagem;
        await axios.put(BACKEND.concat("/receita/", _id), receita);
        _ingredientes.forEach(async ingrediente => {
            if(!ingredientes.includes(ingrediente)) await axios.delete(BACKEND.concat("/ingrediente/", ingrediente.id));
        })
        ingredientes.forEach(async ingrediente => {
            await axios.put(BACKEND.concat("/ingrediente/", ingrediente.id, "/receita/", _id), ingrediente);
        });
        await new Promise(resolve => setTimeout(resolve, 2000));
        navigate("/receitas");
    }
    const onSubmitIngrediente = () => {
        if(ingrediente.item !== "" && ingrediente.quantidade !== "" && ingrediente.medida !== "")   {
            ingrediente.id = index;
            setIndex(index + 1);
            addIngrediente();
            setIngrediente(initialState);
        } else {
            let toast = new bootstrap.Toast(document.getElementById('Toast'));
            toast.show();
        }
    }

    const [espera, setEspera] = useState(false);

    if(user.isNull) { return navigate("/") }
    return (
        <div className="container">
            <div className="py-4">
                <div className="border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-0">Editar Receita</h2>
                    <form className="my-4" onSubmit={(e) => onSubmitReceita(e)}>

                    <div className="form-group row my-2 border border-primary rounded">
                            <label htmlFor="nome" className="col-sm-2 my-2 col-form-label">Foto:</label>
                            <div className="col-sm-10">
                                <input type="file" className="form-control my-2" onChange={ async (event) => {
                                    setImgReceita(event.target.files[0]);
                                    var image = document.getElementById('imagem');
                                    image.src = URL.createObjectURL(event.target.files[0]);
                                }}/>
                                <div><img id="imagem" className="img-thumbnail my-2" style={{'maxWidth': '250px'}} alt="" src={receita.figura} /></div>
                            </div>
                    </div>

                    <div className="form-group row my-4 border border-primary rounded">
                        <label htmlFor="nome" className="col-sm-2 my-2 col-form-label">Nome:</label>
                        <div className="col-sm-10">
                            <input type={"text"} className="form-control my-2" required placeholder="Nome da receita" name="nome" value={nome} onChange={(e) => onReceitaChange(e)} />
                        </div>
                    </div>

                    <div className="form-group row my-4 border border-primary rounded">
                        <label htmlFor="nome" className="col-sm-2 my-2 col-form-label">Ingredientes:</label>
                        <div className="col-sm-10">
                            <div className="form-group row my-2">
                                <label htmlFor="item" className="col-sm-2 col-form-label">Ingrediente:</label>
                                <div className="col-sm-10">
                                    <select className="form-select" aria-label="Ingrediente" name="item" value={item} onChange={(e) => onIngredienteChange(e)} >
                                        <option value={0}></option>
                                        {items.map((i) => (<option value={i.id}>{i.descricao}</option>))}
                                    </select>
                                </div>
                            </div>

                            <div className="form-group row my-2">
                                <label htmlFor="quantidade" className="col-sm-2 col-form-label">Quantidade:</label>
                                <div className="col-sm-10">
                                    <input type={"number"} className="form-control" placeholder="Quantidade" name="quantidade" value={quantidade} onChange={(e) => onIngredienteChange(e)} />
                                </div>
                            </div>

                            <div className="form-group row my-2">
                                <label htmlFor="medida" className="col-sm-2 col-form-label">Unidade de medida:</label>
                                <div className="col-sm-10">
                                    <select className="form-select" aria-label="Medida" name="medida" value={medida} onChange={(e) => onIngredienteChange(e)} >
                                        <option value={0}></option>
                                        {medidasFilter.map((m) => (<option value={m.id}>{m.nome}</option>))}
                                    </select>
                                </div>
                            </div>

                            <div id="Toast" className="toast align-items-center text-bg-danger border-0 position-absolute top-75 start-50 translate-middle" role="alert" aria-live="assertive" aria-atomic="true">
                                <div className="d-flex">
                                    <div className="toast-body">Ingrediente inválido!</div>
                                    <button type="button" className="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                                </div>
                            </div>
                            <button type="button" className="btn btn-outline-primary" onClick={(e) => onSubmitIngrediente(e)}>➕</button>

                            <table className="table table-hover shadow my-4">
                                <thead className="table-secondary">
                                    <tr>
                                    {/* <th scope="col">#</th> */}
                                    <th scope="col">Ingrediente</th>
                                    <th scope="col">Quantidade</th>
                                    <th scope="col">Medida</th>
                                    <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody className="text-center">
                                {
                                    ingredientes.map((ingrediente, id) => (
                                        <tr>
                                        {/* <th scope="row" key={id}>{ingrediente.id}</th> */}
                                        <td>{findItem(ingrediente.item.id)}</td>
                                        <td>{ingrediente.quantidade}</td>
                                        <td>{findMedida(ingrediente.medida.id)}</td>

                                        <td><button type="button" className="btn btn-light" onClick={() => delIngrediente(ingrediente.id)}>➖</button></td>
                                        </tr>
                                    ))
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div className="form-group row my-4 border border-primary rounded">
                        <label htmlFor="nome" className="col-sm-2 my-2 col-form-label">Preparo:</label>
                        <div className="col-sm-10">
                            <textarea className="form-control my-2" rows="4" cols="50" required placeholder="Preparo da receita" name="preparo" value={preparo} onChange={(e) => onReceitaChange(e)} />
                        </div>
                    </div>

                    {espera && <Spinner />}
                    {!espera && <button type="submit" className="btn btn-outline-primary">Atualizar</button>}
                    </form>
                </div>
                <div className="float-end my-4">
                    <Link className="btn btn-outline-dark m-1" to="/receitas">Cancelar</Link>
                </div>
            </div>
        </div>
    )
}