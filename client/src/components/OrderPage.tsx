import { useEffect, useState } from 'react'
import { Input } from "./ui/input"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "./ui/table"
import { Badge } from "./ui/badge"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "./ui/select"
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card"
// import { Search, Filter, ChevronLeft, ChevronRight, Plus, Eye } from 'lucide-react'
import { Button } from './ui/button'
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from './ui/dialog'
import { Label } from './ui/label'
import { Separator } from './ui/separator'
import { ScrollArea } from './ui/scroll-area'
import axiosInstance, { setAuthToken } from '../services/api/axiosInstance'
import store from '../redux/store'
import { getToken, getUser } from '../services/api/auth/AuthService'
import { setUser } from '../redux/slices/userSlice'
import { ChevronLeftIcon, EyeOpenIcon, PlusCircledIcon } from '@radix-ui/react-icons'


const initialOrders = [
  { orderId: 1, user: { name: 'John Doe' }, type: 'Purchase', status: 'Pending', totalAmount: 150.00, orderItems: [{ name: 'Item 1' }, { name: 'Item 2' }] },
  { orderId: 2, user: { name: 'Jane Smith' }, type: 'Sales', status: 'Completed', totalAmount: 250.50, orderItems: [{ name: 'Item 3' }, { name: 'Item 4' }] },
  { orderId: 3, user: { name: 'Bob Johnson' }, type: 'Purchase', status: 'Pending', totalAmount: 75.25, orderItems: [{ name: 'Item 5' }] },
  { orderId: 4, user: { name: 'Alice Brown' }, type: 'Sales', status: 'Completed', totalAmount: 320.00, orderItems: [{ name: 'Item 6' }, { name: 'Item 7' }, { name: 'Item 8' }] },
  { orderId: 5, user: { name: 'Charlie Wilson' }, type: 'Purchase', status: 'Pending', totalAmount: 180.75, orderItems: [{ name: 'Item 9' }, { name: 'Item 10' }] },
]



interface OrderDto {
  orderId: number;
  userId: number;
  type: string;
  status: string;
  totalAmount: number;
}

export const OrderPageComponent: React.FC = () => {
  const [orders, setOrders] = useState<OrderDto[]>([])
  const [searchTerm, setSearchTerm] = useState('')
  const [filterType, setFilterType] = useState('All')
  const [filterStatus, setFilterStatus] = useState('All')
  const [isAddOrderOpen, setIsAddOrderOpen] = useState(false)
  const [newOrder, setNewOrder] = useState<OrderDto>({
    orderId: 0,
    userId: 0,
    type: 'Purchase',
    status: 'Pending',
    totalAmount: 0
  })
  const [selectedOrder, setSelectedOrder] = useState<OrderDto | null>(null)

  useEffect(() => {
  const fetchUserData = async () => {
    try {
      const token = await store.dispatch(getToken);
      setAuthToken(token);

      const user = await getUser();
      console.log("User:", user);
      setUser(user)
      fetchOrders();
    } catch (error) {
      console.error("Error fetching user data:", error);
    }
  };

  fetchUserData();
}, []);

  const fetchOrders = async () => {
    try {
      const response = await axiosInstance.get('/orders')
      setOrders(response.data)
    } catch (error) {
      console.error('Error fetching orders:', error)
    }
  }

  const handleAddOrder = async (e: React.FormEvent) => {
    e.preventDefault()
    try {
      const response = await axiosInstance.post('/orders', newOrder)
      setOrders([...orders, response.data])
      setNewOrder({
        orderId: 0,
        userId: 0,
        type: 'Purchase',
        status: 'Pending',
        totalAmount: 0
      })
      setIsAddOrderOpen(false)
    } catch (error) {
      console.error('Error adding order:', error)
    }
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target
    setNewOrder(prev => ({
      ...prev,
      [name]: name === 'userId' || name === 'totalAmount' ? Number(value) : value
    }))
  }
  return (
    <div className="container mx-auto p-6">
    <h1 className="text-3xl font-bold mb-6">Order Dashboard</h1>

    <Card>
      <CardHeader className="flex flex-row items-center justify-between">
        <CardTitle>Orders</CardTitle>
        <Dialog open={isAddOrderOpen} onOpenChange={setIsAddOrderOpen}>
        <DialogTrigger asChild>
          <Button>
            <PlusCircledIcon className="mr-2 h-4 w-4" /> Add Order
          </Button>
        </DialogTrigger>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Add New Order</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleAddOrder} className="space-y-4">
            <div>
              <Label htmlFor="userId">User ID</Label>
              <Input
                id="userId"
                name="userId"
                type="number"
                value={newOrder.userId}
                onChange={handleInputChange}
                required
              />
            </div>
            <div>
              <Label htmlFor="type">Order Type</Label>
              <Select
                name="type"
                value={newOrder.type}
                onValueChange={(value) => handleInputChange({ target: { name: 'type', value } } as any)}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select order type" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="Purchase">Purchase</SelectItem>
                  <SelectItem value="Sales">Sales</SelectItem>
                </SelectContent>
              </Select>
            </div>
            <div>
              <Label htmlFor="status">Order Status</Label>
              <Select
                name="status"
                value={newOrder.status}
                onValueChange={(value) => handleInputChange({ target: { name: 'status', value } } as any)}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select order status" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="Pending">Pending</SelectItem>
                  <SelectItem value="Completed">Completed</SelectItem>
                </SelectContent>
              </Select>
            </div>
            <div>
              <Label htmlFor="totalAmount">Total Amount</Label>
              <Input
                id="totalAmount"
                name="totalAmount"
                type="number"
                value={newOrder.totalAmount}
                onChange={handleInputChange}
                required
              />
            </div>
            <Button type="submit">Add Order</Button>
          </form>
        </DialogContent>
        </Dialog>
      </CardHeader>
      <CardContent>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Order ID</TableHead>
              <TableHead>User ID</TableHead>
              <TableHead>Type</TableHead>
              <TableHead>Status</TableHead>
              <TableHead>Total Amount</TableHead>
              <TableHead>Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {orders.map((order) => (
              <TableRow key={order.orderId}>
                <TableCell>{order.orderId}</TableCell>
                <TableCell>{order.userId}</TableCell>
                <TableCell>{order.type}</TableCell>
                <TableCell>
                  <Badge variant={order.status === 'Completed' ? 'default' : 'secondary'}>
                    {order.status}
                  </Badge>
                </TableCell>
                <TableCell>${order.totalAmount.toFixed(2)}</TableCell>
                <TableCell>
                  <Dialog>
                    <DialogTrigger asChild>
                      <Button variant="outline" size="sm" onClick={() => setSelectedOrder(order)}>
                        <EyeOpenIcon className="mr-2 h-4 w-4" /> View Details
                      </Button>
                    </DialogTrigger>
                    <DialogContent className="sm:max-w-[625px]">
                        <DialogHeader>
                          <DialogTitle>Order Details</DialogTitle>
                        </DialogHeader>
                        {selectedOrder && (
                          <div className="mt-4 space-y-4">
                            <div className="grid grid-cols-2 gap-4">
                              <div>
                                <h3 className="font-semibold text-sm text-muted-foreground">Order ID</h3>
                                <p className="text-lg">{selectedOrder.orderId}</p>
                              </div>
                              <div>
                                <h3 className="font-semibold text-sm text-muted-foreground">User</h3>
                                <p className="text-lg">{selectedOrder?.user?.name}</p>
                              </div>
                              <div>
                                <h3 className="font-semibold text-sm text-muted-foreground">Type</h3>
                                <p className="text-lg">{selectedOrder.type}</p>
                              </div>
                              <div>
                                <h3 className="font-semibold text-sm text-muted-foreground">Status</h3>
                                <Badge variant={selectedOrder.status === 'Completed' ? 'default' : 'secondary'}>
                                  {selectedOrder.status}
                                </Badge>
                              </div>
                            </div>
                            <Separator />
                            <div>
                              <h3 className="font-semibold text-sm text-muted-foreground mb-2">Items</h3>
                              <ScrollArea className="h-[200px] w-full rounded-md border p-4">
                                <Table>
                                  <TableHeader>
                                    <TableRow>
                                      <TableHead>Item</TableHead>
                                      <TableHead className="text-right">Price</TableHead>
                                    </TableRow>
                                  </TableHeader>
                                  <TableBody>
                                    {selectedOrder?.orderItems?.map((item, index) => (
                                      <TableRow key={index}>
                                        <TableCell>{item.name}</TableCell>
                                        <TableCell className="text-right">${item.price.toFixed(2)}</TableCell>
                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                              </ScrollArea>
                            </div>
                            <Separator />
                            <div className="flex justify-between items-center">
                              <h3 className="font-semibold text-lg">Total Amount</h3>
                              <p className="text-2xl font-bold">${selectedOrder.totalAmount.toFixed(2)}</p>
                            </div>
                          </div>
                        )}
                      </DialogContent>
                  </Dialog>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
    </Card>

    {/* <div className="flex items-center justify-end space-x-2 mt-4">
      <Button variant="outline" size="sm">
        <ChevronLeftIcon className="h-4 w-4 mr-2" />
        Previous
      </Button>
      <Button variant="outline" size="sm">
        Next
        <ChevronRight className="h-4 w-4 ml-2" />
      </Button>
    </div> */}
  </div>
  )
}