// import Usuarios from "./pages/Usuarios";
import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import { user } from './Firebase';
import Home from "./pages/Home";
import Receitas from "./pages/Receitas";
import Usuarios from "./pages/Usuarios";
import AddReceita from "./records/AddReceita";
import AddUsuario from "./records/AddUsuario";

user.setUID(0);
user.setTipoAcesso(1);

require('leaked-handles');

test('verifica se a tela inicial está renderizada corretamente', () => {
    render(<Home />, {wrapper: BrowserRouter})
    expect(screen.getByText(/infnet/i)).toBeInTheDocument()  
});

test('verifica se a tela de listagem de receitas está renderizada corretamente', () => {
    render(<Receitas />, {wrapper: BrowserRouter})
    expect(screen.getByText(/nome da receita/i)).toBeInTheDocument()  
});

test('verifica se a tela de listagem de usuários está renderizada corretamente', () => {
    render(<Usuarios />, {wrapper: BrowserRouter})
    expect(screen.getByText(/nome/i)).toBeInTheDocument()  
});

test('verifica se a tela de inclusão de receitas está renderizada corretamente', () => {
    render(<AddReceita />, {wrapper: BrowserRouter})
    expect(screen.getByText(/cadastro de receitas/i)).toBeInTheDocument()  
});

test('verifica se a tela de inclusão de usuários está renderizada corretamente', () => {
    render(<AddUsuario />, {wrapper: BrowserRouter})
    expect(screen.getByText(/cadastro de usuários/i)).toBeInTheDocument()  
});