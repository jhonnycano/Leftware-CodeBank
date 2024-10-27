import React from 'react';
import { TaskModel } from '../interfaces/TaskModel';

const TaskListTable: React.FC<{ tasks: TaskModel[] }> = ({ tasks }) => {
  console.log('TaskListTable tasks', tasks);
  if (!tasks || !tasks.length) {
    return <div>No tasks yet</div>;
  }

  return (
    <table className='table table-fixed table-bordered table-striped table-responsive table-sm'>
      <thead>
        <tr>
          <th className="col-lg-2">Id</th>
          <th className="col-lg-2">Task</th>
          <th className="col-lg-2">Status</th>
          <th className="col-lg-2">Completed At</th>
        </tr>
      </thead>
      <tbody>
        {tasks.map((task) => (
          <tr key={task.id}>
            <td>{task.id}</td>
            <td>{task.text}</td>
            <td>{task.status}</td>
            <td>{task.completedAt}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default TaskListTable;