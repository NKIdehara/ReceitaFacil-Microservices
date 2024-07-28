import React from 'react';
import { BiExit } from "react-icons/bi";
import { BsFillHouseFill } from "react-icons/bs";
import { Link } from 'react-router-dom';
import { user } from '../Firebase';
import logo from '../resources/images/ic_cook.png';

export default function Navbar() {
    if(!user.isNull) {
        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                    <div className="container-fluid">
                        <div className="float-start">
                            <div><p className="navbar-brand">Receita Fácil</p></div>
                            <div><img alt="" src={logo} className="w-25" /></div>
                        </div>
                        <div className="float-end">
                            {/* <div className="text-warning">Usuário: {user.getUID}</div> */}
                            <div>
                                <Link className="btn btn-outline-light m-2 fa-4x" to="/home"><BsFillHouseFill /> Início</Link>
                                <Link className="btn btn-outline-light m-2" to="/receitas">Receitas</Link>
                                <Link className="btn btn-outline-light m-2" to="/items">Items</Link>
                                <Link className="btn btn-outline-light m-2" to="/usuarios">Usuarios</Link>
                                <Link className="btn btn-outline-light m-2" to="/logout"><BiExit /> Sair</Link>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        )
    }
}