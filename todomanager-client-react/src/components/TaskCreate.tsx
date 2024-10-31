import { useState } from "react";
import { ProjectService } from "../services/apiService";
import { useNavigate, useParams } from "react-router-dom";

const projectService = new ProjectService();

function TaskCreate() {
    const { id } = useParams();
    const navigate = useNavigate();

    const handleSubmit = async (e: any) => {
        e.preventDefault();

        const createTaskResult = await projectService.createTask(id ?? '', text);
        if (createTaskResult.id) {
            navigate(`/projects/${id}`);
        }
    };

    const [text, setText] = useState('');
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="text" className="form-label">Text</label>
                    <input type="text" className="form-control" id="text" name="text" required value={text} onChange={(e) => setText(e.target.value)} />
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    );
}

export default TaskCreate;
