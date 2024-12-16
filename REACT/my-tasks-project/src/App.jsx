import { Route, Routes } from 'react-router-dom'
import './App.css'
import HomePage from './pages/HomePage'
import TasksPage from './pages/TasksPage'

function App() {
  return (
    <Routes>
      <Route path='/' element={<HomePage />}/>
      <Route path='/tasks' element={<TasksPage />}/>
    </Routes>
  )
}

export default App
