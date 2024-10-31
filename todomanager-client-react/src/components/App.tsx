import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'
import AppNavBar from './AppNavBar'
import Home from './Home';
import About from './About';
import ProjectList from './ProjectList';
import ProjectCreate from './ProjectCreate';
import ProjectView from './ProjectView';
import TaskCreate from './TaskCreate';

function App() {
  return (
    <Router>
      <AppNavBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/projects" element={<ProjectList />} />
        <Route path="/projects/new" element={<ProjectCreate />} />
        <Route path="/projects/:id" element={<ProjectView />} />
        <Route path="/projects/:id/tasks/new" element={<TaskCreate />} />
      </Routes>
    </Router>
  )
}

export default App;
