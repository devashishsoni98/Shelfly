import { createBrowserRouter } from "react-router-dom";
import Protected from "./Protected";
import Unprotected from "./Unprotected";
import { OrderPageComponent } from "../components/OrderPage";
import { ProductListingComponent } from "../components/ProductList";
import { InventoryAlerts } from "../components/InverntoryAlert";
import { LandingPage } from "../components/LandingPage";
import LoginFromInv from "../components/LoginFromInv";
import { Dashboard } from "../components/Dashboard";
import { Profiler } from "react";
import { Profile } from "../components/Profile";
import { ContactForm } from "../components/ContactForm";
import { About } from "../components/About";

const Router = createBrowserRouter([
  
      {
        element: <Protected allowedRole="" />,
        children: [
          {
            path: "/dashboard",
            element: <Dashboard />,
          },
          {
            path: "/profile",
            element: <Profile />,
          },
          {
            path: "/product",
            element: <ProductListingComponent />,
          },
          {
            path: "/notifications",
            element: <InventoryAlerts />,
          },
          {
            path: "/order",
            element: <OrderPageComponent />,
          },
          {
            path: "/supplier",
            element: <OrderPageComponent />,
          },
        ],
      },
      {
        element: <Unprotected allowedRole="" />,
        children: [
          { path: "/", element: <LandingPage /> },
          {
            path: "/signup",
            element: <LoginFromInv />,
          },
          {
            path: "/contact",
            element: <ContactForm />,
          },
          {
            path: "/about",
            element: <About />,
          },
        ],
      },
    
  
]);

export default Router;