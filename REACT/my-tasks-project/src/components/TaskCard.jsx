import "./TaskCard.css";
import { useContext } from "react"
import { TaskContext } from "../context/task.context"

function TaskCard({task}) {
    const { updateTask } = useContext(TaskContext);

    const handleInput = (e) => {
        const updatedTask = {...task, title: e.target.value};
        updateTask(updatedTask);
    }

    const handleCheck = () => {
        const updatedTask = {...task, completed: !task.completed};
        updateTask(updatedTask);
    }

    return(
        <article className="task-card">
            <input className="card-title" value={task.title} onChange={handleInput}/>
            <input type="checkbox" checked={task.completed} onChange={handleCheck}/>
        </article>
    )
}

export default TaskCard