import { createContext, useState } from "react";

const TaskContext = createContext();

function TaskProviderWrapper(props){
    const [tasks, setTasks] = useState([]);

    return(
        <TaskContext.Provider value={{tasks, setTasks}}>
            {props.children}
        </TaskContext.Provider>
    )
}

export {TaskContext, TaskProviderWrapper};