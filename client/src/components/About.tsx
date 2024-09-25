'use client'

import { ArrowRightIcon, ArrowTopRightIcon, CheckCircledIcon } from '@radix-ui/react-icons'
// import { ArrowRight, CheckCircle } from 'lucide-react'
import { Link } from 'react-router-dom'

export const About = () => {
  return (
    <div className="min-h-screen flex flex-col bg-white text-gray-900">
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

      <main className="flex-grow container mx-auto px-4 py-8">
        <div className="flex flex-col-reverse md:flex-row items-center gap-8 mb-12">
          <div className="md:w-2/3">
            <h2 className="text-3xl font-bold mb-4">About Shelfly</h2>
            <p className="mb-4">
              Shelfly is a cutting-edge inventory management system designed to streamline your business operations. 
              Our mission is to provide a simple, efficient, and powerful solution for businesses of all sizes.
            </p>
            <p className="mb-4">
              Founded in 2023 by a team of experienced software engineers and supply chain experts, 
              Shelfly has quickly become a leader in the inventory management space, helping hundreds 
              of businesses optimize their stock levels and improve their bottom line.
            </p>
            <h3 className="text-xl font-semibold mb-2">Why Choose Shelfly?</h3>
            <ul className="list-none space-y-2 mb-4">
              {[
                'Real-time inventory tracking',
                'Predictive analytics for stock optimization',
                'Seamless integration with major e-commerce platforms',
                'Customizable reporting and dashboards',
                'Mobile app for on-the-go management'
              ].map((point, index) => (
                <li key={index} className="flex items-center">
                  <CheckCircledIcon className="text-pink-600 mr-2 h-5 w-5" />
                  <span>{point}</span>
                </li>
              ))}
            </ul>
            <a 
              href="#" 
              className="inline-flex items-center text-pink-600 hover:text-pink-700 transition-colors"
            >
              Explore our features in depth
              <ArrowTopRightIcon className="ml-2 h-4 w-4" />
            </a>
          </div>
          <div className="md:w-1/3">
            <img
              src="ko.jpg"
              alt="Shelfly dashboard showcase"
              width={400}
              height={400}
              className="rounded-lg shadow-md"
            />
          </div>
        </div>

        <div className="mb-12">
          <h3 className="text-2xl font-bold mb-4">Our Commitment</h3>
          <p className="mb-4">
            At Shelfly, we're committed to continually improving our platform to meet the evolving 
            needs of modern businesses. Our team of dedicated professionals works tirelessly to 
            ensure that Shelfly remains at the forefront of inventory management technology.
          </p>
          <p>
            We believe in building long-term partnerships with our clients, providing not just 
            software, but a complete inventory management solution backed by exceptional customer 
            support and regular updates.
          </p>
        </div>

        <div>
          <h3 className="text-2xl font-bold mb-4">Join the Shelfly Community</h3>
          <p className="mb-4">
            When you choose Shelfly, you're not just getting a product – you're joining a community 
            of forward-thinking businesses. We regularly host webinars, publish case studies, and 
            share best practices to help our users make the most of their inventory management.
          </p>
          <Link 
            to="/signup" 
            className="inline-flex items-center bg-pink-600 text-white px-4 py-2 rounded hover:bg-pink-700 transition-colors"
          >
            Get started with Shelfly
            <ArrowRightIcon className="ml-2 h-4 w-4" />
          </Link>
        </div>
      </main>

      <footer className="bg-gray-100 py-4 mt-8">
        <div className="container mx-auto px-4 text-center text-sm">
          © 2024 Shelfly. All rights reserved.
        </div>
      </footer>
    </div>
  )
}