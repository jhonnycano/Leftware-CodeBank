import axios from 'axios';
import { ProjectModel } from '../interfaces/ProjectModel';
import { TaskModel } from '../interfaces/TaskModel';

export class ProjectService {
    private baseUrl = import.meta.env.VITE_BACKEND_URL;

    async getProjectList(): Promise<ProjectModel[]> {
        const response = await axios.get(`${this.baseUrl}/projects`);
        return response.data;
    }

    async getProject(projectId: string): Promise<ProjectModel> {
        const response = await axios.get(`${this.baseUrl}/projects/${projectId}`);
        return response.data;
    }

    async createProject(name: string): Promise<ProjectModel> {
        const response = await axios.post(`${this.baseUrl}/projects`, { name });
        return response.data;
    }

    async createTask(projectId: string, text: string): Promise<TaskModel> {
        const payload = { text };
        console.log('createTask payload', payload);
        const response = await axios.post(`${this.baseUrl}/projects/${projectId}/tasks`, payload);
        return response.data;
    }

    async setTaskStatus(projectId: string, taskId: string, status: string): Promise<void> {
        await axios.put(`${this.baseUrl}/projects/${projectId}/tasks/${taskId}/status`, { status });
    }
}