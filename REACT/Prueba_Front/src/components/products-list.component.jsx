import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ProductDataService from "../services/product.service";
import "../styles/ListProducts.css"

const ProductsList = () => {
  const [products, setProducts] = useState([]);
  const [currentProduct, setCurrentProduct] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  //const [searchTitle, setSearchTitle] = useState("");

  useEffect(() => {
    retrieveProducts();
  }, []);

  /*const onChangeSearchTitle = (e) => {
    setSearchTitle(e.target.value);
  };*/

  const retrieveProducts = () => {
    ProductDataService.findAll()
      .then((response) => {
        setProducts(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

//   const refreshList = () => {
//     retrieveProducts();
//     setCurrentProduct(null);
//     setCurrentIndex(-1);
//   };

  const selectProduct = (product, index) => {
    setCurrentProduct(product);
    setCurrentIndex(index);
  };

  /*const removeAllProducts = () => {
    ProductDataService.deleteAll()
      .then((response) => {
        console.log(response.data);
        refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  };*/

  /*const searchByTitle = () => {
    ProductDataService.findByTitle(searchTitle)
      .then((response) => {
        setProducts(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };*/

  return (
    <div className="list row">
      {/* <div className="col-md-8">
        <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search by title"
            value={searchTitle}
            onChange={onChangeSearchTitle}
          />
          <div className="input-group-append">
            <button className="btn btn-outline-secondary" type="button" onClick={searchByTitle}>
              Search
            </button>
          </div>
        </div>
      </div> */}
      <div className="col-md-6">
        <h4>Products List</h4>
        <ul className="list-group">
          {products && products.map((product, index) => (
            <li
              className={"list-group-item " + (index === currentIndex ? "active" : "")}
              onClick={() => selectProduct(product, index)}
              key={index}
            >
              {product.name}
            </li>
          ))}
        </ul>
        {/* <button className="m-3 btn btn-sm btn-danger" onClick={removeAllProducts}>
          Remove All
        </button> */}
      </div>
      <div className="col-md-6">
        {currentProduct ? (
          <div>
            <h4>Product</h4>
            <div>
              <label>
                <strong>Name:</strong>
              </label>{" "}
              {currentProduct.name}
            </div>
            <div>
              <label>
                <strong>Stock:</strong>
              </label>{" "}
              {currentProduct.stock}
            </div>
            <div>
              <label>
                <strong>Brand:</strong>
              </label>{" "}
              {currentProduct.brand}
            </div>
            <div>
              <label>
                <strong>Price:</strong>
              </label>{" "}
              {currentProduct.price}
            </div>
            <div>
              <label>
                <strong>Active:</strong>
              </label>{" "}
              {currentProduct.active ? "Active" : "Inactive"}
            </div>
            <Link to={`/products/${currentProduct.id}`} className="badge badge-warning">
              Edit
            </Link>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Product...</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductsList;
