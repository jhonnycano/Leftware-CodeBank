import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'
import AppNavBar from './AppNavBar'
import Home from './Home';
import About from './About';
import ProjectList from './ProjectList';
import ProjectCreate from './ProjectCreate';

function App() {
  return (
    <Router>
      <AppNavBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/projects" element={<ProjectList />} />
        <Route path="/projects/new" element={<ProjectCreate />} />
      </Routes>
    </Router>
  )
}

export default App;
