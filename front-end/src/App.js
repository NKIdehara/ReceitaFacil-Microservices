import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route, Outlet } from 'react-router-dom';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import Receitas from './pages/Receitas';
import AddReceita from './records/AddReceita';
import EdtReceita from './records/EdtReceita';
import Login from './pages/Login';
import Logout from './pages/Logout';

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

export const BACKEND = "http://localhost:8080";