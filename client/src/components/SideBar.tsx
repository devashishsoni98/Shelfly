import React, { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { Button } from "./ui/button";
import {
  DropdownMenu,
  DropdownMenuTrigger,
  DropdownMenuContent,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuItem,
} from "./ui/dropdown-menu";
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "./ui/tooltip";
// import { BellDotIcon, LogOutIcon } from "lucide-react";
import store from "../redux/store";
import { getToken, getUser, logout } from "../services/api/auth/AuthService";
import { setAuthToken } from "../services/api/axiosInstance";
import { setUser } from "../redux/slices/userSlice";
import { BellIcon, PinLeftIcon } from "@radix-ui/react-icons";
// import { Package2Icon, HomeIcon, PackageIcon, ShoppingCartIcon, BellDotIcon, SettingsIcon } from 'lucide-react';

const Sidebar: React.FC = () => {
    useEffect(() => {
        const fetchUserData = async () => {
          try {
            const token = await store.dispatch(getToken);
            setAuthToken(token);
      
            const user = await getUser();
            console.log("User:", user);
            setUser(user)
          } catch (error) {
            console.error("Error fetching user data:", error);
          }
        };
      
        fetchUserData();
      }, []);
      
    
const navigator = useNavigate();
  const location = useLocation();
  const [activePage, setActivePage] = useState("overview");
  const handlePageChange = (page: string) => {
    setActivePage(page);
  };
  const isActive = (path: string) => location.pathname === path;


  const logOut=()=>{
    logout();
    navigator("/", {replace:true})
  }

  return (

         <aside className=" h-screen justify-between inset-y-0 left-0 z-10 hidden w-14 flex-col border-r bg-background sm:flex">
        <nav className="flex flex-col items-center gap-4 px-2 sm:py-5">
          <TooltipProvider>
            <a
              href="/dashboard"
              className="group flex h-9 w-9 shrink-0 items-center justify-center gap-2 rounded-full bg-primary text-lg font-semibold text-primary-foreground md:h-8 md:w-8 md:text-base"

            >
              <Package2Icon className="h-4 w-4 transition-all group-hover:scale-110" />
              <span className="sr-only">Acme Inc</span>
            </a>
            <Tooltip>
              <TooltipTrigger asChild>
                <a
                  href="/dashboard"
                  className={`flex h-9 w-9 items-center justify-center rounded-lg ${
                    activePage === "dashboard"
                      ? "bg-accent text-accent-foreground"
                      : "text-muted-foreground transition-colors hover:text-foreground"
                  } md:h-8 md:w-8`}
                  onClick={() => handlePageChange("dashboard")}

                >
                  <HomeIcon className="h-5 w-5" />
                  <span className="sr-only">Overview</span>
                </a>
              </TooltipTrigger>
              <TooltipContent side="right">Overview</TooltipContent>
            </Tooltip>
            <Tooltip>
              <TooltipTrigger asChild>
                <a
                  href="/product"
                  className={`flex h-9 w-9 items-center justify-center rounded-lg ${
                    activePage === "inventory"
                      ? "bg-accent text-accent-foreground"
                      : "text-muted-foreground transition-colors hover:text-foreground"
                  } md:h-8 md:w-8`}
                  onClick={() => handlePageChange("product")}

                >
                  <PackageIcon className="h-5 w-5" />
                  <span className="sr-only">Products</span>
                </a>
              </TooltipTrigger>
              <TooltipContent side="right">Products</TooltipContent>
            </Tooltip>
            <Tooltip>
              <TooltipTrigger asChild>
                <a
                  href="/order"
                  className={`flex h-9 w-9 items-center justify-center rounded-lg ${
                    activePage === "orders"
                      ? "bg-accent text-accent-foreground"
                      : "text-muted-foreground transition-colors hover:text-foreground"
                  } md:h-8 md:w-8`}
                  onClick={() => handlePageChange("order")}

                >
                  <ShoppingCartIcon className="h-5 w-5" />
                  <span className="sr-only">Orders</span>
                </a>
              </TooltipTrigger>
              <TooltipContent side="right">Orders</TooltipContent>
            </Tooltip>
            <Tooltip>
            <Tooltip>
              <TooltipTrigger asChild>
                <a
                  href="/notifications"
                  className={`flex h-9 w-9 items-center justify-center rounded-lg ${
                    activePage === "suppliers"
                      ? "bg-accent text-accent-foreground"
                      : "text-muted-foreground transition-colors hover:text-foreground"
                  } md:h-8 md:w-8`}
                  onClick={() => handlePageChange("notifications")}

                >
                  <BellIcon className="h-5 w-5" />
                  <span className="sr-only">Notification</span>
                </a>
              </TooltipTrigger>
              <TooltipContent side="right">Notification</TooltipContent>
            </Tooltip>

              {/* <TooltipTrigger asChild>
                <a
                  href="/settings"
                  className={`flex h-9 w-9 items-center justify-center rounded-lg ${
                    activePage === "settings"
                      ? "bg-accent text-accent-foreground"
                      : "text-muted-foreground transition-colors hover:text-foreground"
                  } md:h-8 md:w-8`}
                  onClick={() => handlePageChange("settings")}

                >
                  <SettingsIcon className="h-5 w-5" />
                  <span className="sr-only">Settings</span>
                </a>
              </TooltipTrigger>
              <TooltipContent side="right">Settings</TooltipContent> */}
            </Tooltip>
          </TooltipProvider>
        </nav>
        <div className="mb-4 flex flex-col items-center">
         
   <Button
              variant="outline"
              size="icon"
              className="overflow-hidden rounded-full"
            >
              <PinLeftIcon onClick={()=>logOut()} />
              <span className="sr-only">Toggle user menu</span>
            </Button>
          
      </div>
      </aside>
  );
};

export default Sidebar;

interface IconProps extends React.SVGProps<SVGSVGElement> {}

function HomeIcon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z" />
      <polyline points="9 22 9 12 15 12 15 22" />
    </svg>
  );
}

function MenuIcon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <line x1="4" x2="20" y1="12" y2="12" />
      <line x1="4" x2="20" y1="6" y2="6" />
      <line x1="4" x2="20" y1="18" y2="18" />
    </svg>
  );
}

function Package2Icon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M3 9h18v10a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V9Z" />
      <path d="m3 9 2.45-4.9A2 2 0 0 1 7.24 3h9.52a2 2 0 0 1 1.8 1.1L21 9" />
      <path d="M12 3v6" />
    </svg>
  );
}

function PackageIcon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="m7.5 4.27 9 5.15" />
      <path d="M21 8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16Z" />
      <path d="m3.3 7 8.7 5 8.7-5" />
      <path d="M12 22V12" />
    </svg>
  );
}

function SearchIcon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <circle cx="11" cy="11" r="8" />
      <path d="m21 21-4.3-4.3" />
    </svg>
  );
}

function SettingsIcon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M12.22 2h-.44a2 2 0 0 0-2 2v.18a2 2 0 0 1-1 1.73l-.43.25a2 2 0 0 1-2 0l-.15-.08a2 2 0 0 0-2.73.73l-.22.38a2 2 0 0 0 .73 2.73l.15.1a2 2 0 0 1 1 1.72v.51a2 2 0 0 1-1 1.74l-.15.09a2 2 0 0 0-.73 2.73l.22.38a2 2 0 0 0 2.73.73l.15-.08a2 2 0 0 1 2 0l.43.25a2 2 0 0 1 1 1.73V20a2 2 0 0 0 2 2h.44a2 2 0 0 0 2-2v-.18a2 2 0 0 1 1-1.73l.43-.25a2 2 0 0 1 2 0l.15.08a2 2 0 0 0 2.73-.73l.22-.39a2 2 0 0 0-.73-2.73l-.15-.08a2 2 0 0 1-1-1.74v-.5a2 2 0 0 1 1-1.74l.15-.09a2 2 0 0 0 .73-2.73l-.22-.38a2 2 0 0 0-2.73-.73l-.15.08a2 2 0 0 1-2 0l-.43-.25a2 2 0 0 1-1-1.73V4a2 2 0 0 0-2-2z" />
      <circle cx="12" cy="12" r="3" />
    </svg>
  );
}

function ShoppingCartIcon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <circle cx="8" cy="21" r="1" />
      <circle cx="19" cy="21" r="1" />
      <path d="M2.05 2.05h2l2.66 12.42a2 2 0 0 0 2 1.58h9.78a2 2 0 0 0 1.95-1.57l1.65-7.43H5.12" />
    </svg>
  );
}

function UsersIcon(props: IconProps) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2" />
      <circle cx="9" cy="7" r="4" />
      <path d="M22 21v-2a4 4 0 0 0-3-3.87" />
      <path d="M16 3.13a4 4 0 0 1 0 7.75" />
    </svg>
  );
}
