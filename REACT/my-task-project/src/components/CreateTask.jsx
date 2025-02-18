import { useContext, useState } from "react";
import { TaskContext } from "../context/task.context";
import "./CreateTask.css"
import { createId } from "../utils/utils";

function CreateTask() {
  const { addTask } = useContext(TaskContext);

  const [taskTitle, setTaskTitle] = useState("");

  const handleInput = (e) => {
    setTaskTitle(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!taskTitle) return;

    const newTask = {
      id: createId(),
      title: taskTitle,
      completed: false,
    };

    addTask(newTask);
    setTaskTitle("");
  };

  return (
    <form className="task-form" onSubmit={handleSubmit}>
      <input
        type="text"
        className="task-title"
        placeholder="Nueva tarea"
        value={taskTitle}
        onChange={handleInput}
      />
      <button className="create-btn">+</button>
    </form>
  );
}

export default CreateTask;
