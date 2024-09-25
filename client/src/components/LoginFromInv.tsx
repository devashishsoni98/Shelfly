import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Card, CardContent } from "./ui/card";
import { Input } from "./ui/input";
import { Button } from "./ui/button";
import { Label } from "./ui/label";
import { Separator } from "./ui/separator";
import { login } from "../services/api/auth/AuthService";

interface Credentials {
  token: string | null;
  expiresIn: number | null;
}

const LoginFromInv: React.FC = () => {
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [error, setError] = useState<string>('');
  const [token, setToken] = useState<Credentials>();
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    if (!email || !password) {
      setError('Please fill in all fields.');
      return;
    }
    try {
      const data = await login(email, password);
      console.log(data);
      setToken(data);
      navigate("/dashboard", { replace: true });
    } catch (error) {
      console.error(error);
      setError("Invalid email or password");
    }
  };

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
    <div className="flex min-h-screen bg-gray-100">
      {/* Left side - Login Form */}
      <div className="w-full md:w-1/2 flex items-center justify-center p-8">
        <Card className="w-full max-w-md">
          <CardContent className="p-6">
            <div className="space-y-6">
              <div>
                <h1 className="text-2xl font-bold">Shelfly</h1>
                <h2 className="text-3xl font-bold mt-4">Welcome back!</h2>
                <p className="text-gray-600 mt-1">Log in to your account.</p>
              </div>
              
              <form onSubmit={handleSubmit} className="space-y-4">
                <div className="space-y-2">
                  <Label htmlFor="email">Email</Label>
                  <Input
                    id="email"
                    type="email"
                    placeholder="Enter your email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                <div className="space-y-2">
                  <div className="flex justify-between">
                    <Label htmlFor="password">Password</Label>
                    {/* <a href="#" className="text-sm text-blue-600 hover:underline">Forgot password?</a> */}
                  </div>
                  <Input
                    id="password"
                    type="password"
                    placeholder="Enter your password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                {error && <p className="text-red-500 text-sm">{error}</p>}
                <Button type="submit" className="w-full">Continue</Button>
              </form>
              
              {/* <div className="relative">
                <Separator className="my-4" />
                <div className="absolute inset-0 flex items-center justify-center">
                  <span className="bg-white px-2 text-gray-500 text-sm">OR</span>
                </div>
              </div>
              
              <Button variant="outline" className="w-full">
                <img src="/api/placeholder/20/20" alt="Google logo" className="mr-2 h-5 w-5" />
                Sign in with Google
              </Button> */}
            </div>
          </CardContent>
        </Card>
      </div>

      {/* Right side - Sample Image */}
      <div className="hidden md:block md:w-1/2 bg-gray-200">
        <div className="h-full flex items-center justify-center">
          <img 
            src="ko.jpg" 
            alt="Shelfly illustration" 
            className="max-w-full max-h-full object-cover"
          />
        </div>
      </div>
    </div>
    </>
  );
};

export default LoginFromInv;