import { useState, useEffect } from 'react';
import { Link, useParams, useSearchParams } from 'react-router-dom';
import TaskListTable from './TaskListTable';
import { ProjectModel } from '../interfaces/ProjectModel';
import { ProjectViewInput } from '../interfaces/ProjectViewInput';
import { ProjectService } from '../services/apiService';

const projectService = new ProjectService();

const ProjectView: React.FC<ProjectViewInput> = ({ mode }) => {
    const { id } = useParams();
    const [searchParams] = useSearchParams();
    const modeFromQuery = searchParams.get('mode');

    const modeToUse = mode ?? modeFromQuery ?? 'table';

    console.log('mode', mode);
    console.log('modeFromQuery', modeFromQuery);
    console.log('modeToUse', modeToUse);

    const [project, setProject] = useState<ProjectModel | null>(null);

    useEffect(() => {
        const fetchProject = async () => {
            try {
                const fetchedProject = await projectService.getProject(id ?? '');
                setProject(fetchedProject);
            } catch (error) {
                console.error('Error fetching project:', error);
            }
        };

        fetchProject();
    }, [id]);

    if (!project) {
        return <div>Loading project...</div>;
    }

    return (
        <div>
            <h2>{project.name}</h2>
            {modeToUse === 'table'
                ? <TaskListTable tasks={project.tasks} />
                : <div />
            }
            <Link to={`/projects/${project.id}/tasks/new`}>Create task</Link>
        </div>
        
    );
};

export default ProjectView;