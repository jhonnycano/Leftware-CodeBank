import { TaskModel } from "./TaskModel";

export interface ProjectModel {
    id: string;
    name: string;
    tasks: TaskModel[];
};