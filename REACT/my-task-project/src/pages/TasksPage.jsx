import { useContext, useEffect } from "react";
import HeaderComponent from "../components/HeaderComponent";
import { TaskContext } from "../context/task.context";
import TaskCard from "../components/TaskCard";
import CreateTask from "../components/CreateTask";
import "./TasksPage.css"

function TasksPage() {
  const { tasks, getTasks, hasError, hasLoaded } = useContext(TaskContext);

  useEffect(() => {
    getTasks();
  }, [])

  const taskCards = tasks.map((task) => (
    <li key={task.id}>
      <TaskCard task={task}></TaskCard>
    </li>
  ));

  return (
    <>
      <HeaderComponent></HeaderComponent>

      <section id="tasks-page">
        <h2 className="title">Tasks</h2>
        <ul className="task-list">
          <li>
            <CreateTask></CreateTask>
          </li>
          {hasError ? (
            <h2>No se ha podido obtener las tareas</h2>
          ) : !hasLoaded ? (
            <h2>Cargando...</h2>
          ) : (
            taskCards
          )
          }
        </ul>
      </section>
    </>
  );
}

export default TasksPage;
