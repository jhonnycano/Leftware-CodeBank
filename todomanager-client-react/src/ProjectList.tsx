import { useState, useEffect } from 'react';
import axios from 'axios';

interface Project {
    name: string;
}

const ProjectList = () => {
    const [projects, setProjects] = useState<Project[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchProjects = async () => {
            try { 
                const response = await axios.get('http://localhost:8080/api/projects');
                setProjects(response.data);
            } catch (error) {
                setError('Error fetching projects');
            }
        };

        fetchProjects();
    }, []);

    return (
        <div>
            {error && <p>{error}</p>}
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    {projects.map((project) => (
                        <tr key={project.name}>
                            <td>{project.name}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProjectList;