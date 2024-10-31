import { useState } from "react";
import { ProjectService } from "../services/apiService";
import { useNavigate } from "react-router-dom";

const projectService = new ProjectService();

function ProjectCreate() {
    const navigate = useNavigate();

    const handleSubmit = async (e: any) => {
        e.preventDefault();

        const createProjectResult = await projectService.createProject(name);
        if (createProjectResult.id) {
            navigate('/projects');
        }
    };

    const [name, setName] = useState('');
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control" id="name" name="name" required value={name} onChange={(e) => setName(e.target.value)} />
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    );
}

export default ProjectCreate;
