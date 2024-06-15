import * as bootstrap from 'bootstrap';
import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { BACKEND } from '../App';
import { storage } from '../Firebase';
import { ref, uploadBytes, getDownloadURL } from 'firebase/storage';
import { v4 } from 'uuid';
import Spinner from '../layout/Spinner';

    export default function EdtReceita() {
        const location = useLocation();
        const { _id, _nome, _ingredientes, _preparo, _usuario, _figura } = location.state;

        let navigate = useNavigate();

        const [imgReceita, setImgReceita] = useState(null);
        const [receita, setReceita] = useState({
            id: _id,
            nome: _nome,
            preparo: _preparo,
            usuario: _usuario,
            figura: _figura
        });

        const [index, setIndex] = useState(99999);
        const [ingredientes, setIngredientes] = useState(_ingredientes);
        const addIngrediente = () => {
            setIngredientes([...ingredientes, ingrediente]);
        };
        const delIngrediente = (idIngrediente) => {
            const novaLista = ingredientes.filter(i => i.idIngrediente !== idIngrediente);
            setIngredientes(novaLista);
        };
        const initialState = {
            idIngrediente: "",
            item: "",
            quantidade: "",
            medida: ""
        }
        const [ingrediente, setIngrediente] = useState(initialState);

        const {id, nome, preparo, usuario, figura} = receita;
        const {idIngrediente, item, quantidade, medida} = ingrediente;

        const onReceitaChange = (e) => {
            setReceita({...receita, [e.target.name]:e.target.value});
        }
        const onIngredienteChange = (e) => {
            setIngrediente({...ingrediente, [e.target.name]:e.target.value});
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
                console.log(ingrediente);
                if(!ingredientes.includes(ingrediente)) await axios.delete(BACKEND.concat("/ingrediente/", ingrediente.idIngrediente));
            })
            ingredientes.forEach(async ingrediente => {
                await axios.put(BACKEND.concat("/ingrediente/", ingrediente.idIngrediente, "/receita/", _id), ingrediente);
            });
            navigate("/receitas");
        }
        const onSubmitIngrediente = () => {
            if(ingrediente.item !== "" && ingrediente.quantidade !== "" && ingrediente.medida !== ""){
                ingrediente.idIngrediente = index;
                setIndex(index + 1);
                addIngrediente();
                setIngrediente(initialState);
            } else {
                let toast = new bootstrap.Toast(document.getElementById('Toast'));
                toast.show();
            }
        }

        const [espera, setEspera] = useState(false);

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
                                    <input type={"text"} className="form-control" placeholder="Nome do Ingrediente" name="item" value={item} onChange={(e) => onIngredienteChange(e)} />
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
                                    <input type={"text"} className="form-control" placeholder="g / ml / colheres" name="medida" value={medida} onChange={(e) => onIngredienteChange(e)} />
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
                                        {/* <th scope="row" key={id}>{ingrediente.idIngrediente}</th> */}
                                        <td>{ingrediente.item}</td>
                                        <td>{ingrediente.quantidade}</td>
                                        <td>{ingrediente.medida}</td>
                                        <td><button type="button" className="btn btn-light" onClick={() => delIngrediente(ingrediente.idIngrediente)}>➖</button></td>
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