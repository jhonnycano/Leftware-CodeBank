import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { ProjectModel } from '../interfaces/ProjectModel';
import { ProjectService } from '../services/apiService';

const projectService = new ProjectService();

const ProjectList = () => {
    const [projects, setProjects] = useState<ProjectModel[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchProjects = async () => {
            try {
                const fetchedProjects = await projectService.getProjectList();
                setProjects(fetchedProjects);
            } catch (error) {
                setError('Error fetching projects');
                console.error('Error fetching projects:', error);
            }
        };

        fetchProjects();
    }, []);

    return (
        <div>
            {error && <p>{error}</p>}
            <table className='table table-fixed table-bordered table-striped table-responsive table-sm'>
                <thead>
                    <tr>
                        <th className="col-sm-2">Id</th>
                        <th className="col-sm-2">Name</th>
                        <th className="col-sm-2"></th>
                    </tr>
                </thead>
                <tbody>
                    {projects.map((project) => (
                        <tr key={project.id}>
                            <td>{project.id}</td>
                            <td>{project.name}</td>
                            <td>
                                <Link to={'/projects/' + project.id + '?mode=table'}>View</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProjectList;