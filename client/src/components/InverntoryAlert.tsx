// 'use client'

import { useEffect, useState } from "react";
import { Trash2 } from "lucide-react";
import { Button } from "./ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "./ui/card";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "./ui/alert-dialog";
import axiosInstance, { setAuthToken } from "../services/api/axiosInstance";
import store from "../redux/store";
import { getToken, getUser } from "../services/api/auth/AuthService";
import { setUser } from "../redux/slices/userSlice";


interface ProductDto {
  productId: number;
  name: string;
  description: string;
  quantity: number;
  price: number;
}

export const InventoryAlerts = () => {
  const [alerts, setAlerts] = useState<ProductDto[]>([
    {
      productId: 1,
      name: "Widget A",
      description: "A high-quality widget",
      quantity: 5,
      price: 19.99,
    },
    {
      productId: 2,
      name: "Gadget B",
      description: "An innovative gadget",
      quantity: 3,
      price: 29.99,
    },
    {
      productId: 3,
      name: "Tool C",
      description: "A durable tool",
      quantity: 8,
      price: 39.99,
    },
    {
      productId: 4,
      name: "Device D",
      description: "A smart device",
      quantity: 2,
      price: 49.99,
    },
  ]);

  const fetchAlerts = async () => {
    try {
      const response = await axiosInstance.get("/inventory/low-stock");
      setAlerts(response.data); // Update alerts with fetched data
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = await store.dispatch(getToken);
        setAuthToken(token);

        const user = await getUser();
        console.log("User:", user);
        setUser(user);
        fetchAlerts(); // Fetch alerts after fetching user data
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };

    fetchUserData();
  }, []);

  return (
    <div className=" w-full min-h-screen bg-background flex flex-col">
      <header className=" text-pink-600 py-4 px-6">
        <h1 className="text-2xl font-bold">Stock Alerts</h1>
      </header>
      <p className="text-sm text-muted-foreground  px-6 ">
        Last updated: {new Date().toLocaleString()}
      </p>
      <main className="flex-grow p-6">
        <Card className="w-full max-w-5xl mx-auto">
          <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
            <CardTitle className="text-2xl font-bold">
              Low Stock Alerts
            </CardTitle>
            {/* <AlertDialog>
              <AlertDialogTrigger asChild>
                <Button variant="destructive" className="text-sm">Delete All</Button>
              </AlertDialogTrigger>
              <AlertDialogContent>
                <AlertDialogHeader>
                  <AlertDialogTitle>Are you absolutely sure?</AlertDialogTitle>
                  <AlertDialogDescription>
                    This action cannot be undone. This will permanently delete all alerts.
                  </AlertDialogDescription>
                </AlertDialogHeader>
                <AlertDialogFooter>
                  <AlertDialogCancel>Cancel</AlertDialogCancel>
                  <AlertDialogAction onClick={()=>{}}>Continue</AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog> */}
          </CardHeader>
          <CardDescription className="px-6">
            {alerts.length} items require attention
          </CardDescription>
          <CardContent className="pt-4">
            {alerts.map((product) => (
              <div
                key={product.productId}
                className=" bg-red-100 mb-4 p-4 border rounded-lg flex flex-col sm:flex-row sm:items-center sm:justify-between"
              >
                <div className="mb-2 sm:mb-0">
                  <h3 className="font-semibold">{product.name}</h3>
                  <p className="text-sm text-muted-foreground">
                    {product.description}
                  </p>
                  <p className="text-sm">
                    Quantity:{" "}
                    <span className="font-semibold text-destructive">
                      {product.quantity}
                    </span>{" "}
                    | Price:{" "}
                    <span className="font-semibold">
                      ${product.price.toFixed(2)}
                    </span>
                  </p>
                </div>
                {/* <Button variant="ghost" size="icon" onClick={() => {}} className="self-end sm:self-auto">
                  <Tras2 className="h-4 w-4" />
                  <span className="sr-only">Delete alert for {product.name}</span>
                </Button> */}
              </div>
            ))}
            {alerts.length === 0 && (
              <p className="text-center text-muted-foreground">
                No low stock alerts
              </p>
            )}
          </CardContent>
          <CardFooter></CardFooter>
        </Card>
      </main>
    </div>
  );
};
