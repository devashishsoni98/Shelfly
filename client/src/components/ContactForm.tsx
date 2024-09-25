import React, { useRef, useState } from 'react'
import emailjs from '@emailjs/browser'
import { Button } from "./ui/button"
import { Input } from "./ui/input"
import { Textarea } from "./ui/textarea"
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card"
import { Link } from 'react-router-dom'

export const ContactForm : React.FC= ()=>  {
  const form = useRef<HTMLFormElement>(null)
  const [isValidEmail, setIsValidEmail] = useState(true)

  const sendEmail = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    if (!isValidEmail) {
      alert('Please enter a valid email address.')
      return
    }

    if (form.current) {
      emailjs.sendForm('service_6yivj7k', 'template_6me6y1i', form.current, '62vzB0X2_bmYYhcY5')
        .then((result) => {
          alert('Message sent successfully!')
          form.current?.reset()
        }, (error) => {
          alert('Something went wrong! Try again after some time.')
        })
    }
  }

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const enteredEmail = e.target.value
    setIsValidEmail(/\S+@\S+\.\S+/.test(enteredEmail))
  }

  return (
    <>
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
    <div className="flex min-h-screen items-center justify-center bg-white p-4">
      <Card className="w-full max-w-4xl ">
        <CardHeader>
          <CardTitle className="text-2xl font-bold text-center ">Contact Us</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div className="hidden md:block">
              <img src="ko.jpg" alt="Contact illustration" className="w-full h-auto" />
            </div>
            <form ref={form} onSubmit={sendEmail} className="space-y-4">
              <div>
                <label htmlFor="user_name" className="block text-sm font-medium text-gray-200">Name</label>
                <Input
                  type="text"
                  id="user_name"
                  name="user_name"
                  required
                  className="mt-1 block w-full"
                />
              </div>
              <div>
                <label htmlFor="user_email" className="block text-sm font-medium text-gray-200">Email</label>
                <Input
                  type="email"
                  id="user_email"
                  name="user_email"
                  required
                  className={`mt-1 block w-full ${!isValidEmail ? 'border-red-500' : ''}`}
                  onChange={handleEmailChange}
                  />
              </div>
              <div>
                <label htmlFor="message" className="block text-sm font-medium text-gray-200">Message</label>
                <Textarea
                  id="message"
                  name="message"
                  required
                  className="mt-1 block w-full"
                  rows={4}
                />
              </div>
              <Button type="submit" className="w-full">
                Send
              </Button>
            </form>
          </div>
        </CardContent>
      </Card>
    </div>
    </>
  )
}