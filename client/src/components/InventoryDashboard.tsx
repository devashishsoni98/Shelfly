import React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "./ui/table"
import { Badge } from "./ui/badge"
import { Package, DollarSign, AlertTriangle, BarChart2 } from 'lucide-react'


const inventoryMetrics = [
  { title: "Total Items", value: 1234, icon: Package },
  { title: "Total Value", value: "$87,650", icon: DollarSign },
  { title: "Low Stock Items", value: 15, icon: AlertTriangle },
  { title: "Categories", value: 8, icon: BarChart2 },
];

const inventoryItems = [
  { id: 1, name: "Widget A", category: "Electronics", quantity: 50, price: "$10.99" },
  { id: 2, name: "Gadget B", category: "Accessories", quantity: 100, price: "$24.99" },
  { id: 3, name: "Tool C", category: "Hardware", quantity: 25, price: "$15.50" },
  { id: 4, name: "Device D", category: "Electronics", quantity: 75, price: "$49.99" },
  { id: 5, name: "Part E", category: "Spare Parts", quantity: 200, price: "$5.99" },
];

const InventoryDashboard:React.FC = () => {
  return (
    <div className="p-8">
      <h1 className="text-3xl font-bold mb-6">Inventory Management Dashboard</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
        {inventoryMetrics.map((metric, index) => (
          <Card key={index}>
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">
                {metric.title}
              </CardTitle>
              <metric.icon className="h-4 w-4 text-muted-foreground" />
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">{metric.value}</div>
            </CardContent>
          </Card>
        ))}
      </div>
      
      <Card>
        <CardHeader>
          <CardTitle>Inventory Items</CardTitle>
        </CardHeader>
        <CardContent>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Name</TableHead>
                <TableHead>Category</TableHead>
                <TableHead>Quantity</TableHead>
                <TableHead>Price</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {inventoryItems.map((item) => (
                <TableRow key={item.id}>
                  <TableCell className="font-medium">{item.name}</TableCell>
                  <TableCell>
                    <Badge variant="secondary">{item.category}</Badge>
                  </TableCell>
                  <TableCell>{item.quantity}</TableCell>
                  <TableCell>{item.price}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </CardContent>
      </Card>
    </div>
  );
};

export default InventoryDashboard;
