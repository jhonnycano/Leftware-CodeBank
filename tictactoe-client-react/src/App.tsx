// import { useState } from 'react'
import './App.css'
import SetUser from './SetUser'

function onSessionStart(msg: any) {
  console.log(msg);
}
function App() {
  return (
    <SetUser onSessionStart={onSessionStart}></SetUser>
  )
}

export default App
