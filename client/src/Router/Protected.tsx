import React, { useEffect } from 'react'
import { useSelector } from 'react-redux';
import { Outlet, useNavigate } from 'react-router-dom';
import { RootState } from '../redux/store';
import Sidebar from '../components/SideBar';


interface ProtectedProps {
    allowedRole: string;
  }

const Protected: React.FC<ProtectedProps> = ({allowedRole }) => {
  const { isAuthenticated } = useSelector((state: RootState) => state.auth);

    const navigator = useNavigate();

        useEffect(()=>{
            if (!isAuthenticated) {
              console.log("Auth: ",isAuthenticated);
              
                // return <Navigate to="signup" replace />
                navigator('/signup', { replace: true });
            }
        },[allowedRole, navigator])

  return (
    <div className='flex  '>
      <Sidebar/>
      <Outlet/>
    </div>
  )
}

export default Protected
