import "./App.css"
import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import AddProduct from "./components/add-product.component";
import EditProduct from "./components/edit-product.component";
import ProductsList from "./components/products-list.component";
import DeleteProduct from "./components/delete-product.component";
import BuyProduct from "./components/buy-product.component";

class App extends Component{
  render(){
    return (
      <div className="main">
        <nav className="navbar navbar-expand navbar-dark bg-dark d-flex align-items-center">
          <Link to={"/products"} className="navbar-brand">
            Productos
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/products"} className="nav-link">
                Productos
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Añadir
              </Link>
            </li>
          </div>
          
        </nav>

        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<ProductsList />} />
            <Route path="/products" element={<ProductsList />} />
            <Route path="/add" element={<AddProduct />} />
            <Route path="/products/:id" element={<EditProduct />} />
            <Route path="/products/:id/delete" element={<DeleteProduct />} />
            <Route path="/products/:id/buy" element={<BuyProduct />} />
          </Routes>
        </div>
      </div>
    );
  }
  
}

export default App;
