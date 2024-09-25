import { useEffect, useState } from "react";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  ArcElement,
} from "chart.js";
import { Bar, Pie } from "react-chartjs-2";
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "./ui/table";
import { Button } from "./ui/button";
import { Input } from "./ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "./ui/select";
import store from "../redux/store";
import { getToken, getUser } from "../services/api/auth/AuthService";
import axiosInstance, { setAuthToken } from "../services/api/axiosInstance";
import { setUser } from "../redux/slices/userSlice";
import { Link } from "react-router-dom";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  ArcElement,
  Title,
  Tooltip,
  Legend
);

export const Dashboard: React.FC = () => {
  const [orders, setOrders] = useState([
    { orderId: 1, type: "Purchase", status: "Completed", totalAmount: 1000 },
    { orderId: 2, type: "Sales", status: "Pending", totalAmount: 1500 },
    { orderId: 3, type: "Purchase", status: "Completed", totalAmount: 800 },
  ]);

  const [products, setProducts] = useState([
    { productId: 1, name: "Product A", quantity: 100, price: 10 },
    { productId: 2, name: "Product B", quantity: 50, price: 20 },
    { productId: 3, name: "Product C", quantity: 75, price: 15 },
  ]);

  const fetchOrders = async () => {
    try {
      const response = await axiosInstance.get("/orders");
      setOrders(response.data.slice(0, 10)); // Update orders state with fetched data
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  };

  // Function to fetch products
  const fetchProducts = async () => {
    try {
      const response = await axiosInstance.get("/inventory/check/all");
      setProducts(response.data.slice(0, 10)); // Update products state with fetched data
    } catch (error) {
      console.error("Error fetching products:", error);
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
        fetchProducts();
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };

    fetchUserData();
    fetchOrders();
    fetchProducts();
  }, []);

  const orderData = {
    labels: ["Purchase", "Sales"],
    datasets: [
      {
        data: [
          orders.filter((order) => order.type === "Purchase").length,
          orders.filter((order) => order.type === "Sales").length,
        ],
        backgroundColor: ["rgba(255, 99, 132, 0.5)", "rgba(53, 162, 235, 0.5)"],
        borderColor: ["rgba(255, 99, 132, 1)", "rgba(53, 162, 235, 1)"],
        borderWidth: 1,
      },
    ],
  };

  const productData = {
    labels: products.map((product) => product.name),
    datasets: [
      {
        label: "Product Quantity",
        data: products.map((product) => product.quantity),
        backgroundColor: "rgba(75, 192, 192, 0.6)",
        borderColor: "rgba(75, 192, 192, 1)",
        borderWidth: 1,
      },
    ],
  };

  const pieOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: "bottom" as const,
      },
      title: {
        display: true,
        text: "Order Types",
      },
    },
  };

  const barOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: "top" as const,
      },
      title: {
        display: true,
        text: "Product Inventory",
      },
    },
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Shelfly Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4  ">
        <Card>
          <CardHeader>
            <CardTitle>Order Summary</CardTitle>
          </CardHeader>
          <CardContent className="h-[50vh] mx-auto flex justify-center">
            <Pie data={orderData} options={pieOptions} />
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Product Inventory</CardTitle>
          </CardHeader>
          <CardContent>
            <Bar options={barOptions} data={productData} />
          </CardContent>
        </Card>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
        <Card>
          <CardHeader>
            <CardTitle>Recent Orders</CardTitle>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Order ID</TableHead>
                  <TableHead>Type</TableHead>
                  <TableHead>Status</TableHead>
                  <TableHead>Total Amount</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {orders.map((order) => (
                  <TableRow key={order.orderId}>
                    <TableCell>{order.orderId}</TableCell>
                    <TableCell>{order.type}</TableCell>
                    <TableCell>{order.status}</TableCell>
                    <TableCell>${order.totalAmount.toFixed(2)}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Product List</CardTitle>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Product ID</TableHead>
                  <TableHead>Name</TableHead>
                  <TableHead>Quantity</TableHead>
                  <TableHead>Price</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {products.map((product) => (
                  <TableRow key={product.productId}>
                    <TableCell>{product.productId}</TableCell>
                    <TableCell>{product.name}</TableCell>
                    <TableCell>{product.quantity}</TableCell>
                    <TableCell>${product.price.toFixed(2)}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </CardContent>
        </Card>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>Quick Actions</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="flex flex-wrap gap-4">
            <Link to="/order">
              <Button>Create New Order</Button>
            </Link>
            <Link to="/product">
              <Button>Add New Product</Button>
            </Link>
            {/* <Select>
              <SelectTrigger className="w-[180px]">
                <SelectValue placeholder="Filter Orders" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All Orders</SelectItem>
                <SelectItem value="purchase">Purchase Orders</SelectItem>
                <SelectItem value="sales">Sales Orders</SelectItem>
              </SelectContent>
            </Select>
            <Input placeholder="Search products..." className="max-w-sm" /> */}
          </div>
        </CardContent>
      </Card>
    </div>
  );
};
