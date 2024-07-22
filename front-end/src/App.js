import { Outlet, Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import Logout from './pages/Logout';
import Receitas from './pages/Receitas';
import AddReceita from './records/AddReceita';
import EdtReceita from './records/EdtReceita';

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route path="/logout" element={<Logout />} />
                    <Route element={
                        <>
                        <Navbar />
                        <Outlet />
                        </>
                    }>
                        <Route path="/home" element={<Home />} />
                        <Route path="/receitas" element={<Receitas />} />
                        <Route path="/addReceita" element={<AddReceita />} />
                        <Route path="/edtReceita" element={<EdtReceita />} />
                    </Route>
                </Routes>
            </Router>
        </div>
    );
}

export default App;

// export const BACKEND = "http://localhost:8080";
export const BACKEND = "https://receitafacil-microservices-backend.azurewebsites.net";
