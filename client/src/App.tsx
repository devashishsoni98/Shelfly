import React from 'react';
import {RouterProvider } from 'react-router-dom';
import Router from "./Router/Router"

const App: React.FC = () => {
  return (
    <>
    <div className="bg-white ">

      <RouterProvider router={Router} />
    </div>
    </>
  );
};

export default App;