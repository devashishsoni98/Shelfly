

import { useEffect, useState } from 'react'
// import { Plus, Edit, Trash2, Filter } from 'lucide-react'
import { Button } from "./ui/button"
import { Input } from "./ui/input"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "./ui/table"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "./ui/card"
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from "./ui/dialog"
import { Label } from "./ui/label"
import axiosInstance, { setAuthToken } from '../services/api/axiosInstance'
import store from '../redux/store'
import { getToken, getUser } from '../services/api/auth/AuthService'
import { setUser } from '../redux/slices/userSlice'
import { CaretUpIcon, Pencil1Icon, PlusCircledIcon, TrashIcon } from '@radix-ui/react-icons'

interface ProductDto {
  productId: number
  name: string
  description: string
  quantity: number
  price: number
}

export const ProductListingComponent:React.FC =()=> {
  const [products, setProducts] = useState<ProductDto[]>([])
  const [filter, setFilter] = useState('')
  const [editingProduct, setEditingProduct] = useState<ProductDto | null>(null)
  const [isAddDialogOpen, setIsAddDialogOpen] = useState(false)
  const [statusFilter, setStatusFilter] = useState<string>(''); 
const [typeFilter, setTypeFilter] = useState<string>(''); 
  

  
  const filteredProducts = products.filter(product => 
    product.name.toLowerCase().includes(filter.toLowerCase()) ||
    product.description.toLowerCase().includes(filter.toLowerCase())
  )

  const totalProducts = products.length
  const totalQuantity = products.reduce((sum, product) => sum + product.quantity, 0)
  const totalValue = products.reduce((sum, product) => sum + (product.quantity * product.price), 0)


  const fetchProducts = async () => {
    try {
      const response = await axiosInstance.get('/inventory/check/all')
      setProducts(response.data) 
    } catch (error) {
      console.error("Error fetching products:", error)
    }
  }

  const handleAdd = async (newProduct: ProductDto) => {
    setIsAddDialogOpen(false)
    await fetchProducts() 
  }

  const handleEdit = async (editedProduct: ProductDto) => {
    setProducts(products.map(p => p.productId === editedProduct.productId ? editedProduct : p))
    setEditingProduct(null)
    await fetchProducts() 
  }

  const handleDelete = async(productId: number) => {
  setProducts(products.filter(p => p.productId !== productId))
  try {
    const response = await axiosInstance.delete(`/product/${productId}`);
    console.log(response.data);
  } catch (error) {
    
  }
  }


  useEffect(() => {
    fetchProducts()
  }, [])

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = await store.dispatch(getToken);
        setAuthToken(token);
  
        const user = await getUser();
        setUser(user)
        fetchProducts();
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };
  
    fetchUserData();
  }, []);



  return (
    <div className="container mx-auto p-4">
      <Card className="mb-6">
        <CardHeader>
          <CardTitle>Product Overview</CardTitle>
          <CardDescription>Quick summary of your product inventory</CardDescription>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div>
              <p className="text-sm font-medium">Total Products</p>
              <p className="text-2xl font-bold">{totalProducts}</p>
            </div>
            <div>
              <p className="text-sm font-medium">Total Quantity</p>
              <p className="text-2xl font-bold">{totalQuantity}</p>
            </div>
            <div>
              <p className="text-sm font-medium">Total Value</p>
              <p className="text-2xl font-bold">${totalValue.toFixed(2)}</p>
            </div>
          </div>
        </CardContent>
      </Card>

      <div className="flex justify-between items-center mb-4">
        <div className="flex items-center">
          <CaretUpIcon className="mr-2" />
          <Input
            placeholder="Filter products..."
            value={filter}
            onChange={(e) => setFilter(e.target.value)}
            className="max-w-sm"
          />
        </div>
        <Dialog open={isAddDialogOpen} onOpenChange={setIsAddDialogOpen}>
          <DialogTrigger asChild>
            <Button><PlusCircledIcon className="mr-2 h-4 w-4" /> Add Product</Button>
          </DialogTrigger>
          <DialogContent>
            <ProductForm onSubmit={handleAdd} />
          </DialogContent>
        </Dialog>
      </div>

      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>Name</TableHead>
            <TableHead>Description</TableHead>
            <TableHead>Quantity</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {filteredProducts.map((product) => (
            <TableRow key={product.productId}>
              <TableCell>{product.name}</TableCell>
              <TableCell>{product.description}</TableCell>
              <TableCell>{product.quantity}</TableCell>
              <TableCell>${product.price.toFixed(2)}</TableCell>
              <TableCell>
                <div className="flex space-x-2">
                  <Dialog>
                    <DialogTrigger asChild>
                      <Button variant="outline" size="icon" onClick={() => setEditingProduct(product)}>
                        <Pencil1Icon className="h-4 w-4" />
                      </Button>
                    </DialogTrigger>
                    <DialogContent>
                      <ProductForm product={product} onSubmit={handleEdit} />
                    </DialogContent>
                  </Dialog>
                  <Button variant="outline" size="icon" onClick={() => handleDelete(product.productId)}>
                    <TrashIcon className="h-4 w-4" />
                  </Button>
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}

interface ProductFormProps {
  product?: ProductDto;
  onSubmit: (product: ProductDto) => void;
}

function ProductForm({ product, onSubmit }: ProductFormProps) {
  const [formData, setFormData] = useState<ProductDto>(
    product || { productId: 0, name: '', description: '', quantity: 0, price: 0 }
  );

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'quantity' || name === 'price' ? parseFloat(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      let response;
      if(!product){
         response = await axiosInstance.post('/product', formData)
      }
      else{
         response = await axiosInstance.put(`/product/${product?.productId}`, formData)
      }

      if (response.status !== 200) {
        throw new Error('Failed to create product')
      }

      onSubmit(response.data); 
    } catch (error) {
      console.error('Error creating product:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <DialogHeader>
        <DialogTitle>{product ? 'Edit Product' : 'Add New Product'}</DialogTitle>
        <DialogDescription>
          {product ? 'Make changes to the product here.' : 'Enter the details for the new product.'}
        </DialogDescription>
      </DialogHeader>
      <div className="grid gap-4 py-4">
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="name" className="text-right">Name</Label>
          <Input id="name" name="name" value={formData.name} onChange={handleChange} className="col-span-3" />
        </div>
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="description" className="text-right">Description</Label>
          <Input id="description" name="description" value={formData.description} onChange={handleChange} className="col-span-3" />
        </div>
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="quantity" className="text-right">Quantity</Label>
          <Input id="quantity" name="quantity" type="number" value={formData.quantity} onChange={handleChange} className="col-span-3" />
        </div>
        <div className="grid grid-cols-4 items-center gap-4">
          <Label htmlFor="price" className="text-right">Price</Label>
          <Input id="price" name="price" type="number" step="0.01" value={formData.price} onChange={handleChange} className="col-span-3" />
        </div>
      </div>
      <DialogFooter>
        <Button type="submit">{product ? 'Save Changes' : 'Add Product'}</Button>
      </DialogFooter>
    </form>
  );
}