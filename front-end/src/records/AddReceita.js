import React, { useState } from 'react';
import axios from 'axios';
import { format } from "date-fns";
import { Link, useNavigate } from 'react-router-dom';
import { BACKEND } from '../App';
import { user, storage } from '../Firebase';
import { ref, uploadBytes, getDownloadURL } from 'firebase/storage';
import { v4 } from 'uuid';
import Spinner from '../layout/Spinner';

    export default function AddReceita() {
        let navigate = useNavigate();
        var imagem = "https://firebasestorage.googleapis.com/v0/b/infnet-receitafacil.appspot.com/o/ic_food_0.png?alt=media&token=af33f298-dbcc-40ea-aa8c-ed1430a46a57";

        const [imgReceita, setImgReceita] = useState(null);
        const [receita, setReceita] = useState({
            nome: "",
            ingredientes: "",
            preparo: "",
            data_receita: format(new Date(), "yyyy-MM-dd"),
            usuario: user.getUID,
            figura: imagem
        });

        const {nome, ingredientes, preparo, data_receita, usuario, figura} = receita;

        const onInputChange = (e) => {
            setReceita({...receita, [e.target.name]:e.target.value});
        }

        const uploadImgReceita = () => {
            if(imgReceita == null) return null;
            const imageRef = ref(storage, `${imgReceita.name + v4()}`);
            return uploadBytes(imageRef, imgReceita).then((snapshot) => {
                return getDownloadURL(snapshot.ref).then((url) => { return url });
            });
        };

        const onSubmit = async (e) => {
            e.preventDefault();
            setEspera(true);
            var imagem = await uploadImgReceita();
            if(imagem !== null) receita.figura = imagem;
            const result = await axios.post(BACKEND.concat("/receita"), receita);
            navigate("/receitas");
        }

        const [espera, setEspera] = useState(false);

    return (
        <div className="container">
            <div className="py-4">
                <div className="border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-0">Cadastro de Receitas</h2>
                    <form className="my-4" onSubmit={(e) => onSubmit(e)}>
                    <div className="form-group row my-2">
                            <label htmlFor="nome" className="col-sm-2 col-form-label">Foto:</label>
                            <div className="col-sm-10">
                                <input type="file" className="form-control" onChange={ async (event) => {
                                    setImgReceita(event.target.files[0]);
                                    var image = document.getElementById('imagem');
                                    image.src = URL.createObjectURL(event.target.files[0]);
                                }}/>
                                <div><img id="imagem" className="img-thumbnail" style={{'maxWidth': '250px'}} alt="" /></div>
                            </div>
                        </div>
                        <div className="form-group row my-2">
                            <label htmlFor="nome" className="col-sm-2 col-form-label">Nome:</label>
                            <div className="col-sm-10">
                                <input type={"text"} className="form-control" required placeholder="Nome da receita" name="nome" value={nome} onChange={(e) => onInputChange(e)} />
                            </div>
                        </div>
                        <div className="form-group row my-2">
                            <label htmlFor="nome" className="col-sm-2 col-form-label">Ingredientes:</label>
                            <div className="col-sm-10">
                                <textarea className="form-control" rows="4" cols="50" required placeholder="Ingredientes" name="ingredientes" value={ingredientes} onChange={(e) => onInputChange(e)} />
                            </div>
                        </div>
                        <div className="form-group row my-2">
                            <label htmlFor="nome" className="col-sm-2 col-form-label">Preparo:</label>
                            <div className="col-sm-10">
                                <textarea className="form-control" rows="4" cols="50" required placeholder="Preparo da receita" name="preparo" value={preparo} onChange={(e) => onInputChange(e)} />
                            </div>
                        </div>
                        {espera && <Spinner />}
                        {!espera && <button type="submit" className="btn btn-outline-primary">Cadastrar</button>}
                    </form>
                </div>
                <div className="float-end my-4">
                    <Link className="btn btn-outline-dark m-1" to="/receitas">Cancelar</Link>
                </div>
            </div>
        </div>
    )
}