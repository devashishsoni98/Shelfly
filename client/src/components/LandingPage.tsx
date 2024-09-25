import { useEffect } from "react"
import store from "../redux/store"
import { logout } from "../redux/slices/authSlice"
import { Link } from "react-router-dom"

export const LandingPage:React.FC =()=> {

  useEffect(()=>{
    store.dispatch(logout());
  })

  return (
    <div className="min-h-screen bg-white">
      <header className="container mx-auto px-4 py-6 flex items-center justify-between">
        <a href="/" className="text-2xl font-bold text-red-500">
          Shelfly
        </a>
        <nav className="hidden md:flex space-x-6">
          <Link to="/contact" className="text-gray-600 hover:text-gray-900">Contact Us</Link>
          <Link to="/about" className="text-gray-600 hover:text-gray-900">About Us</Link>
        </nav>
        <div className="flex items-center space-x-4">
          {/* <Link to="/signup" className="text-gray-600 hover:text-gray-900">
            Login
          </Link> */}
          <Link
            to="/signup"
            className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 transition duration-300"
          >
            Login
          </Link>
        </div>
      </header>
      <main className="container mx-auto px-4 py-16 text-center">
        <h1 className="text-5xl font-bold mb-6">
          Explore Our Inventory
          <br />
          Management <span className="text-red-500">Features</span>
        </h1>
        <p className="text-xl text-gray-600 mb-8 max-w-2xl mx-auto">
          Discover how Shelfly simplifies inventory with features designed for ease and organization.
        </p>
        <div className="flex justify-center space-x-4">
          <Link
            to="/signup"
            className="bg-red-500 text-white px-6 py-3 rounded-md hover:bg-red-600 transition duration-300"
          >
            Explore More
          </Link>
          <Link
            to="/signup"
            className="text-red-500 px-6 py-3 rounded-md border border-red-500 hover:bg-red-50 transition duration-300"
          >
            Get Started
          </Link>
        </div>
      </main>
    </div>
  )
}