import { createContext, useState } from "react";

const TaskContext = createContext();

function TaskProviderWrapper(props){
    const [tasks, setTasks] = useState([]);

    const API_URL = "https://caa8da634762f8b45c48.free.beeceptor.com/api/tasks/"

    const getTasks = async () => {
        try{
            console.log("Get Tasks");
            const response = await fetch(API_URL);
            const data = await response.json();
            setTasks(data);
        }catch(e){
            console.log(e);
        }
    }

    const addTask = async (newTask) => {
        try {
            await fetch(API_URL, {
                method: "POST",
                body: JSON.stringify(newTask)
            })
            setTasks([newTask, ...tasks])
        } catch (e) {
            console.log(e); 
        }
    }

    const updateTask = (updatedTask) => {
        const updatedTasks = tasks.map((task) => {
            if (task.id !== updatedTask.id) return task;
            return updatedTask; 
        })

        setTasks(updatedTasks)
    }

    return(
        <TaskContext.Provider value={{tasks, setTasks, getTasks, addTask, updateTask}}>
            {props.children}
        </TaskContext.Provider>
    );
}

export {TaskContext, TaskProviderWrapper};