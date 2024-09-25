import { Sidebar } from 'lucide-react'
import React from 'react'
import { Outlet } from 'react-router-dom'

const DashboradRoot = () => {
  return (
    <>
    <Sidebar/>
     <Outlet/>   
    </>
  )
}

export default DashboradRoot