import React, { useState, useEffect } from "react";
import ProductDataService from "../services/product.service";
import { useParams } from "react-router-dom";

const EditProduct = () => {
    const { id } = useParams();
    const [stock, setStock] = useState(0);
    const [name, setName] = useState("");
    const [brand, setBrand] = useState(""); 
    const [price, setPrice] = useState(0); 
    const [active, setActive] = useState(false);
    const [submitted, setSubmitted] = useState(false);

    useEffect(() => {
        getProduct(id);
    }, [id]);

    const getProduct = (id) => {
        ProductDataService.get(id)
            .then(response => {
                setStock(response.data.stock);
                setName(response.data.name);
                setBrand(response.data.brand);
                setPrice(response.data.price);
                setActive(response.data.active);
            })
            .catch(error => {
                console.error("Error fetching product:", error);
            });
    };

    const handleCheckboxChange = (event) => {
        setActive(event.target.checked);
    };

    const updateProduct = () => {
        const data = {
            stock: stock,
            name: name,
            brand: brand,
            price: price,
            active: active
        };

        ProductDataService.update(id, data)
            .then(response => {
                setSubmitted(true);
            })
            .catch(error => {
                console.error("Error updating product:", error);
            });
    };

    return (
        <div className="submit-form">
            {
                submitted ? (
                    <div>
                        <h4>Product updated successfully!</h4>
                    </div>
                ) : (
                    <div>
                    <div className="form-group">
                        <label htmlFor="id">Id</label>
                        <input type="text" className="form-control" id="id" name="id" value={id} required readOnly />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="stock">Stock</label>
                        <input type="number" className="form-control" id="stock" name="stock" value={stock} required onChange={(e) => setStock(e.target.value)} />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input className="form-control" type="text" id="name" name="name" value={name} required onChange={(e) => setName(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="brand">Brand</label>
                        <input className="form-control" type="text" id="brand" name="brand" value={brand} required onChange={(e) => setBrand(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input className="form-control" type="number" step="0.01" id="price" name="price" value={price} required onChange={(e) => setPrice(e.target.value)} />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="active">Active &nbsp;</label>
                        <input type="checkbox" id="active" name="active" checked={active} onChange={handleCheckboxChange} />
                    </div>
    
                    <div>
                        <button type="submit" className="btn btn-success" onClick={updateProduct}>
                            Update
                        </button>
                    </div>
                </div>
                )
            }
            
        </div>
    );
};

export default EditProduct;
