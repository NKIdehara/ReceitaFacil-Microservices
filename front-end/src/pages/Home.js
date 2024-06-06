import React from 'react'
import infnet from '../resources/images/ic_infnet.png';
import { user } from '../Firebase';

export default function Home() {
    if(!user.isNull) {
        return (
            <div className="container-fluid">
                <div><img src={infnet} className="w-25 m-3" style={{'maxWidth': '200px'}} /></div>
                <div>
                    <h1>Instituto Infnet</h1>
                    <h2>Engenharia de Software</h2>
                </div>
                <div className="p-5">
                    <h3>Projeto de Bloco: Engenharia de Softwares Escaláveis</h3>
                    <div><a>Aluno: Nelson Kenji Idehara</a></div>
                    <div><a>Professor: Leonardo Silva da Gloria</a></div>
                </div>
                <div className="p-6">
                    <h5>Projeto Original</h5>
                    <h6>Projeto de Bloco: Engenharia Disciplinada de Softwares</h6>
                    <div><a>Professor: Armênio Torres Santiago Cardoso</a></div>
                </div>
            </div>
        )
    }
}